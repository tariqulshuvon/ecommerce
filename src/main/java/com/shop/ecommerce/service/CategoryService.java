package com.shop.ecommerce.service;

import com.shop.ecommerce.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll();

    Optional<Category> findById(long id);

    Optional<Category> findByName(String name);

    void save(Category category);

    void delete(Long id);

}
