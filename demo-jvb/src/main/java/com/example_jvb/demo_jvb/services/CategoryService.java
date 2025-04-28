package com.example_jvb.demo_jvb.services;

import com.example_jvb.demo_jvb.model.Category;
import com.example_jvb.demo_jvb.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }

}
