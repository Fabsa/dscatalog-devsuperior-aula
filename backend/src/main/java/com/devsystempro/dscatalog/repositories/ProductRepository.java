//CAMADADA DE ACESSO A DADOS

package com.devsystempro.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsystempro.dscatalog.entities.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
	

}
