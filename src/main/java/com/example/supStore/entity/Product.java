package com.example.supStore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="name")
	private String name;
	@Column(name="listPrice")
	private Double listPrice;
	@Column(name="ourPrice")
	private Double ourPrice;
	private String category;
	private String description;
	private String brand;
	private Boolean active= true;
	private int inStockNumber;
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	@Transient
	private MultipartFile pPhoto;


	public Product(Integer id,String name, Double listPrice, Double ourPrice, String category, String description, String brand,
			Boolean active, int inStockNumber, MultipartFile pPhoto) {
		super();
		this.id=id;
		this.name = name;
		this.listPrice = listPrice;
		this.ourPrice = ourPrice;
		this.category = category;
		this.description = description;
		this.brand = brand;
		this.active = active;
		this.inStockNumber = inStockNumber;
		this.pPhoto = pPhoto;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Double getListPrice() {
		return listPrice;
	}


	public void setListPrice(Double listPrice) {
		this.listPrice = listPrice;
	}


	public Double getOurPrice() {
		return ourPrice;
	}


	public void setOurPrice(Double ourPrice) {
		this.ourPrice = ourPrice;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public Boolean getActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}


	public int getInStockNumber() {
		return inStockNumber;
	}


	public void setInStockNumber(int inStockNumber) {
		this.inStockNumber = inStockNumber;
	}


	public MultipartFile getpPhoto() {
		return pPhoto;
	}


	public void setpPhoto(MultipartFile pPhoto) {
		this.pPhoto = pPhoto;
	}


	public Product() {
		super();
	}
	
}
