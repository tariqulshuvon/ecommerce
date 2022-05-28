package com.shop.ecommerce.service;

import com.shop.ecommerce.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll();

    public Category findById(long id);

    void save(Category category);

    void delete(Long id);

}
