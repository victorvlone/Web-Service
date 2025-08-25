package com.webserive.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.webserive.demo.entities.Category;
import com.webserive.demo.services.CategoryService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/new")
    public ResponseEntity<?> newCategory(@RequestBody String name) {
        try {
            Category category = categoryService.createCategory(name);
            return ResponseEntity.status(201).body("Categoria criada com sucesso: " + category.getName());
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("erro ao criar categoria");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id){
        try{
            Category category = categoryService.deleteCategory(id);
            return ResponseEntity.status(200).body("Categoria com ID: " + category.getId() + " deletada com sucesso!");
        } catch(RuntimeException e){
            return ResponseEntity.status(404).body("erro ao deletar categoria: " + e.getMessage());
        }
    }
    
}
