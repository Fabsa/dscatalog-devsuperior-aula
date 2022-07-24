

package com.devsystempro.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;//vai registrar como um componente que vai participar do sistema de injeção do spring  
import org.springframework.transaction.annotation.Transactional;

import com.devsystempro.dscatalog.entities.Category;
import com.devsystempro.dscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {//gerenciar os objetos do carteroryservice e o spring

@Autowired
private CategoryRepository repository;
	
    @Transactional(readOnly=true)//readOnly evita de travar o banco em uma transação
	public List<Category>findAll(){	 
	
		return repository.findAll();
	}
			
}
