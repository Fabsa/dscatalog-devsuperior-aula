package com.devsystempro.dscatalog.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
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

import com.devsystempro.dscatalog.dto.CategoryDTO;
import com.devsystempro.dscatalog.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
		
	@GetMapping
	public ResponseEntity<Page<CategoryDTO>>findAll(Pageable pageable){
			/*@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy
			
			*/		
		Page<CategoryDTO> list = service.findAllPaged(pageable);
		
		return ResponseEntity.ok().body(list);
	}
	
	//metodo para obter uma categoria por Id	
	
	@GetMapping(value="/{id}")
	public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){		
		CategoryDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<CategoryDTO>insert(@RequestBody CategoryDTO dto){
	 dto = service.insert(dto);	
	 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();//instanciando URI usada para inseriri no cabeçalho da resposta o location
	 return ResponseEntity.created(uri).body(dto);
	}
	//o metodo Atualizar sera uma mescala dos metodos para criar uma categoria e o de inserir.
	
	@PutMapping(value="/{id}")//rota que recebe o id
	public ResponseEntity<CategoryDTO>update(@PathVariable Long id,@RequestBody CategoryDTO dto){//retorna a nova categoria atualizada para mim
	 dto = service.update(id,dto);	
	 return ResponseEntity.ok().body(dto);//retorna uma resposta 200 ok padrao
	}
	
	//metodo Delete 
	@DeleteMapping(value="/{id}")//rota que recebe o id 
	public ResponseEntity<CategoryDTO>delete(@PathVariable Long id){
    service.delete(id);	
	 return ResponseEntity.noContent().build();//retorna uma resposta 204 de vazio
	}
	
	
}
