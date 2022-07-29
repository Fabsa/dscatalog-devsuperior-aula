package com.devsystempro.dscatalog.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.devsystempro.dscatalog.entities.Category;
import com.devsystempro.dscatalog.entities.Product;

public class ProductDTO implements Serializable {
	private static final long serialVersionUID = 1L; 

	private Long id;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;	
	private Instant date;
	
	private List<CategoryDTO> categories = new ArrayList<>();//Lista da classse CategoryDTO
	
	public ProductDTO() {
		
	}	
	
	public ProductDTO(Long id, String name, String description, Double price, String imgUrl, Instant date) {
	    this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
		this.date = date;
	}

public ProductDTO(Product entity) {
	this.id = entity.getId();
	this.name = entity.getName();
	this.description =entity.getDescription() ;
	this.price = entity.getPrice();
	this.imgUrl = entity.getImgUrl();
	this.date = entity.getDate();
	}
	
  public ProductDTO(Product entity,Set<Category>categories) {//sobre carga --> que tras para ser usado no front end um list de categorias a ser selecionadas usando o mesmo connstrutor usando argumentos diferentes.
	                                                          //  assim podemos instanciar os elementos da categoria e alimentar a List<CategoryDTO>categories.
	  this(entity);                                           //aqui ele tras todos os elementos recebidos na variavel entity 
	  categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));//o forEach pega cada elemento e insere ele tranformado para dto na list da categories.
  }

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public Double getPrice() {
	return price;
}

public void setPrice(Double price) {
	this.price = price;
}

public String getImgUrl() {
	return imgUrl;
}

public void setImgUrl(String imgUrl) {
	this.imgUrl = imgUrl;
}

public Instant getDate() {
	return date;
}

public void setDate(Instant date) {
	this.date = date;
}

public List<CategoryDTO> getCategories() {
	return categories;
}

public void setCategories(List<CategoryDTO> categories) {
	this.categories = categories;
}

	
}
