package com.webserive.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webserive.demo.entities.Category;
import com.webserive.demo.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        return categoryRepository.save(category);
    }

    public List<Category> listCategory() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new RuntimeException("Não existe nenhuma categoria salva!");
        }
        return categories;
    }

    public Category findCategory(String categoryName) {
        Optional<Category> category = categoryRepository.findByName(categoryName);
        return category.orElseThrow(
                () -> new RuntimeException("Categoria '" + categoryName + "' não encontrada!"));
    }

    public Category deleteCategory(Integer id) {
        return categoryRepository.findById(id)
                .map(category -> {
                    categoryRepository.delete(category);
                    return category;
                })
                .orElseThrow(() -> new RuntimeException("Categoria com id " + id + " não encontrada!"));
    }

}
