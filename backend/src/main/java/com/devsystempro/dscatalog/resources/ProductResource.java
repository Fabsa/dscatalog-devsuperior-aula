package com.devsystempro.dscatalog.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsystempro.dscatalog.dto.ProductDTO;
import com.devsystempro.dscatalog.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	
	@Autowired
	private ProductService service;
		
	@GetMapping
	public ResponseEntity<Page<ProductDTO>>findAll(Pageable pageable ){
						
		Page<ProductDTO> list = service.findAllPaged(pageable);
		
		return ResponseEntity.ok().body(list);
	}
	
	//metodo para obter uma categoria por Id	
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id){		
		ProductDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<ProductDTO>insert(@RequestBody ProductDTO dto){
	 dto = service.insert(dto);	
	 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();//instanciando URI usada para inseriri no cabeçalho da resposta o location
	 return ResponseEntity.created(uri).body(dto);
	}
	//o metodo Atualizar sera uma mescala dos metodos para criar uma categoria e o de inserir.
	
	@PutMapping(value="/{id}")//rota que recebe o id
	public ResponseEntity<ProductDTO>update(@PathVariable Long id,@RequestBody ProductDTO dto){//retorna a nova categoria atualizada para mim
	 dto = service.update(id,dto);	
	 return ResponseEntity.ok().body(dto);//retorna uma resposta 200 ok padrao
	}
	
	//metodo Delete 
	@DeleteMapping(value="/{id}")//rota que recebe o id 
	public ResponseEntity<ProductDTO>delete(@PathVariable Long id){
    service.delete(id);	
	 return ResponseEntity.noContent().build();//retorna uma resposta 204 de vazio
	}
	
	
}
