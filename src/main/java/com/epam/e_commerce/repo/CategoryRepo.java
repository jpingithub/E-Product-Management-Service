package com.epam.e_commerce.repo;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.e_commerce.entity.Category;

@Repository
public interface CategoryRepo extends CrudRepository<Category, Integer> {
	Optional<Category> findByValue(String value);
//	@Query("SELECT DISTINCT c.value FROM Category c")
//	Set<String> findAllDistinctByValue();
}
