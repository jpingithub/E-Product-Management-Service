package com.epam.e_commerce.repo;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.e_commerce.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
	Optional<Product> findByCompanyAndModel(String Company,String model);
	Set<Product> findByCategoryId(Integer categoryId);
	Optional<Product> findByCategoryIdAndId(Integer categoryId,Integer id);
	Integer countByCategoryId(Integer categoryId);
}
