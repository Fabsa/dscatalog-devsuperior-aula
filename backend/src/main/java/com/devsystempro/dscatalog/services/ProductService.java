package com.devsystempro.dscatalog.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;//vai registrar como um componente que vai participar do sistema de injeção do spring  
import org.springframework.transaction.annotation.Transactional;

import com.devsystempro.dscatalog.dto.CategoryDTO;
import com.devsystempro.dscatalog.dto.ProductDTO;
import com.devsystempro.dscatalog.entities.Category;
import com.devsystempro.dscatalog.entities.Product;
import com.devsystempro.dscatalog.repositories.CategoryRepository;
import com.devsystempro.dscatalog.repositories.ProductRepository;
import com.devsystempro.dscatalog.services.exceptions.DatabaseException;
import com.devsystempro.dscatalog.services.exceptions.ResourceNotFoundException;


@Service
public class ProductService {//gerenciar os objetos do carteroryservice e o spring

	@Autowired
	private ProductRepository repository;//responsavel por fazeer o acesso ao banco de dados.
	@Autowired
	private CategoryRepository categoryRepository;
	
	//busca todos cas categorias com controle de quantidades de itens por pagina
	
	    @Transactional(readOnly=true)//readOnly evita de travar o banco em uma transação
		public Page<ProductDTO>findAllPaged(Pageable pegeable){	//pega todos os registros	
		//2° forma  com uso de expressão çambda
	      Page<Product>list = repository.findAll(pegeable);
		  return list.map(x -> new ProductDTO(x));//stream permite trabalhar com funçoes de alta ordem, onde pega cada elemento da lista original e aplicanado nele um ProductDTO(x)
					
	}
	    //busca todas categorias por ID;
	    
	    @Transactional(readOnly=true)
		public ProductDTO findById(Long id) {			
			Optional<Product>obj = repository.findById(id);
			Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
			return new ProductDTO(entity,entity.getCategories());
		}
	    @Transactional
		public ProductDTO insert(ProductDTO dto) {
			Product entity = new Product();
			copyDtoToEntity(dto,entity);
			entity = repository.save(entity);
			return new ProductDTO(entity);
		}
	   
		@Transactional
		public ProductDTO update(Long id,ProductDTO dto){
	     try {	
		 Product entity = repository.getOne(id);//entidade estanciada na memoria
		 copyDtoToEntity(dto,entity);
		 entity = repository.save(entity);
		 return new ProductDTO(entity);
	     }
	     catch(EntityNotFoundException e) {
	    	throw new ResourceNotFoundException("Id not Found"+id); 
	     }	    
	     
	   }
       //service DELETE
	    
		public void delete(Long id) {
			try {
		repository.deleteById(id);	
		
			}catch(EmptyResultDataAccessException e) {
				  throw new ResourceNotFoundException("Id not found"+id);
			}
			catch(DataIntegrityViolationException e) {
				throw new DatabaseException("Integraty violation ");
			}			 
		}	
		private void copyDtoToEntity(ProductDTO dto, Product entity) {//classe auxiliar para povoar a entidade produtc
			entity.setName(dto.getName());
			entity.setDescription(dto.getDescription());
			entity.setDate(dto.getDate());
			entity.setImgUrl(dto.getImgUrl());
			entity.setPrice(dto.getPrice());
			
			entity.getCategories().clear();//limpa o conjunto de categorias da entidade.
			for(CategoryDTO catDto : dto.getCategories()) {//acessa a lista com as categories
				Category category = categoryRepository.getOne(catDto.getId());//estancia uma categoria sem mexer no banco de dados ainda.
				entity.getCategories().add(category);
			}
		}
	}



//1° Forma
//List<ProductDTO>listDto = new arrayList<>();
//for(Product cat:list){
//   listDto.add(new ProductDTO(cat));
//return listDto