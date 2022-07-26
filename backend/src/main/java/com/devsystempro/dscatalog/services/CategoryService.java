package com.devsystempro.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;//vai registrar como um componente que vai participar do sistema de injeção do spring  
import org.springframework.transaction.annotation.Transactional;

import com.devsystempro.dscatalog.dto.CategoryDTO;
import com.devsystempro.dscatalog.entities.Category;
import com.devsystempro.dscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {//gerenciar os objetos do carteroryservice e o spring

	@Autowired
	private CategoryRepository repository;//responsavel por fazeer o acesso ao banco de dados.
	
	
	//busca todos cas categorias
	
	    @Transactional(readOnly=true)//readOnly evita de travar o banco em uma transação
		public List<CategoryDTO>findAll(){		
		//2° forma  com uso de expressão çambda
	      List<Category>list = repository.findAll();
		  return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());//stream permite trabalhar com funçoes de alta ordem, onde pega cada elemento da lista original e aplicanado nele um CategoryDTO(x)
					
	}
	    //busca todas categorias por ID
	    
	    @Transactional(readOnly=true)
		public CategoryDTO findById(Long id) {			
			Optional<Category>obj = repository.findById(id);
			Category entity = obj.get();
			return new CategoryDTO(entity);
		}

		
}

//1° Forma
//List<CategoryDTO>listDto = new arrayList<>();
//for(Category cat:list){
//   listDto.add(new CategoryDTO(cat));
//return listDto