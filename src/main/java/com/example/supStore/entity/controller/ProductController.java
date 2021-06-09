package com.example.supStore.entity.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.example.supStore.entity.Product;
import com.example.supStore.service.ProductService;

@Controller
public class ProductController {

	
	@Autowired
    private static ProductService productService;
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public static String addProduct(Model model) {
		
		Product product = new Product();
		model.addAttribute("product", product);
		return "addNewProduct";
		
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public static String addProductPost(@ModelAttribute("product") Product product, HttpServletRequest request) {
productService.save(product);
MultipartFile pPhoto= product.getpPhoto();

try {
	byte[] bytes= pPhoto.getBytes();
	String name= product.getId()+".png";
	BufferedOutputStream  stream =  new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/product/"+name)));
	stream.write(bytes);
	stream.close();
} catch (Exception e) {
e.printStackTrace();
}
		return "redirect:productList";
		
	}
	@RequestMapping("/productList")
	public static String productList(Model model) {
		
List<Product> productList = productService.findAll();
model.addAttribute("productList", productList);
		return "productList";
		
	}
}
