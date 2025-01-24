package com.epam.e_commerce.service;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.epam.e_commerce.client.CartClient;
import com.epam.e_commerce.dto.ProductRequest;
import com.epam.e_commerce.entity.Category;
import com.epam.e_commerce.entity.Product;
import com.epam.e_commerce.exception.NoProductWithCategoryAndIdException;
import com.epam.e_commerce.exception.NoProductsFroundWithCategoryException;
import com.epam.e_commerce.exception.ProductAlreadyExistsException;
import com.epam.e_commerce.exception.ProductNotFoundWithIdException;
import com.epam.e_commerce.repo.CategoryRepo;
import com.epam.e_commerce.repo.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepository proRepo;
	private final CategoryRepo catRepo;
	private final ModelMapper mapper;
	private final CartClient cartClient;

	public Product saveProduct(ProductRequest req) {
		String company = req.getCompany(), model = req.getModel();
		Optional<Product> productFromDB = proRepo.findByCompanyAndModel(company, model);
		if (productFromDB.isEmpty()) {
			Product product = mapper.map(req, Product.class);
			Category category = product.getCategory();
			category.setValue(category.getValue().toLowerCase());
			product.setCategory(category);
			String categoryValue = product.getCategory().getValue();
			Optional<Category> categoryFromDatabase = catRepo.findByValue(categoryValue);
			Product savedProduct;
			if (categoryFromDatabase.isEmpty())
				savedProduct = proRepo.save(product);
			else {
				product.setCategory(categoryFromDatabase.get());
				savedProduct = proRepo.save(product);
			}
			return savedProduct;
		} else
			throw new ProductAlreadyExistsException("Product already found in database");
	}

	public Set<Product> getProdctsByCategory(String category) {
		category = category.toLowerCase();
		Category categoryFromDB = catRepo.findByValue(category).get();
		Set<Product> productsFromDB = proRepo.findByCategoryId(categoryFromDB.getId());
		if (productsFromDB.isEmpty())
			throw new NoProductsFroundWithCategoryException("Products not present as : " + category);
		return productsFromDB;
	}

	public Product getByCategoryAndId(String category, Integer id) {
		Category categoryFromDB = catRepo.findByValue(category).get();
		Integer categoryId = categoryFromDB.getId();
		Optional<Product> productResult = proRepo.findByCategoryIdAndId(categoryId, id);
		if (productResult.isPresent())
			return productResult.get();
		else
			throw new NoProductWithCategoryAndIdException("Product not found");
	}

	public Product deleteById(Integer id) {
		Product product = getById(id);
		proRepo.deleteById(product.getId());
		cartClient.deleteByProductIdFromCart(id);
		cartClient.deleteByProductIdFromPurchase(id);
		return product;
	}
	
	public Product getById(Integer id) {
		return proRepo.findById(id).orElseThrow(()->new ProductNotFoundWithIdException("No product found with id : " + id));
	}
	
	public List<Product> allProducts(){
		return (List<Product>) proRepo.findAll();
	}
	
	public Set<String> categories(){
		Iterator<Category> iterator = catRepo.findAll().iterator();
		Set<String> categories = new LinkedHashSet<>();
		while(iterator.hasNext()) {
			Category currentCategory = iterator.next();
			if(proRepo.countByCategoryId(currentCategory.getId())>0) {
				categories.add(currentCategory.getValue());
			}
		}
		return categories;
	}
	public Product updateProduct(Integer id,ProductRequest req) {
	Product byId = getById(id);
	Product updateProduct = mapper.map(req, Product.class);
	Category category = updateProduct.getCategory();
	category.setValue(category.getValue().toLowerCase());
	updateProduct.setCategory(category);
	updateProduct.setId(byId.getId());
	Product result;
	Optional<Category> categoryFromDb = catRepo.findByValue(updateProduct.getCategory().getValue());
	if(categoryFromDb.isEmpty()) result = proRepo.save(updateProduct);
	else {
		updateProduct.setCategory(categoryFromDb.get());
		result = proRepo.save(updateProduct);
	}
	return result;
	}
	
}
