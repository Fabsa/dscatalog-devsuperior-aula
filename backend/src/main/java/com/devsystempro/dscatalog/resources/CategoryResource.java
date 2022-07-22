package com.devsystempro.dscatalog.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsystempro.dscatalog.entities.Category;

@RestController
@RequestMapping(value = "/categories")//rota do recurso no plural
public class CategoryResource {//recurso da entidade e um controlador REST da entidade Category
	
	//end point (salvar, editar...)
	@GetMapping
	public ResponseEntity<List<Category>>findAll(){
		List<Category>list = new ArrayList<>();
		list.add(new Category(1L, "Books"));
		list.add(new Category(2L, "Eletronics"));
		
		return ResponseEntity.ok().body(list);
	}
	
}
