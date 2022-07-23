

package com.devsystempro.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;//vai registrar como um componente que vai participar do sistema de injeção do spring  

import com.devsystempro.dscatalog.entities.Category;
import com.devsystempro.dscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {//gerenciar os objetos do carteroryservice e o spring

@Autowired
private CategoryRepository repository;
	
	public List<Category>findAll(){	 
	
		return repository.findAll();
	}
			
}
