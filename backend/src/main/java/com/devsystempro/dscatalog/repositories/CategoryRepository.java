//CAMADADA DE ACESSO A DADOS

package com.devsystempro.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsystempro.dscatalog.entities.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{
	

}
