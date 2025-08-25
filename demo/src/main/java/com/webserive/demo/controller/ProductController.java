package com.webserive.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.webserive.demo.DTO.ProductDTO;
import com.webserive.demo.entities.Product;
import com.webserive.demo.services.ProductService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/new")
    public ResponseEntity<?> newProduct(@RequestBody ProductDTO dto) {
        try {
            Product product = productService.newProduct(dto);
            return ResponseEntity.ok().body("Produto cadastrado com sucesso: " + product.getName());
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteproduct(@PathVariable Integer id) {
        try {
            Product product = productService.deleteProduct(id);
            return ResponseEntity.status(200).body("Produto com ID: " + product.getId() + " deletado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("erro ao deletar produto: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> allProducts() throws Exception {
        try{
            List<Product> products = productService.allProducts();
            return ResponseEntity.ok().body(products);
        } catch(RuntimeException e){
            return ResponseEntity.status(404).body("Erro ao listar produtos: " + e.getMessage());
        }
    }

    @GetMapping("/byCategory")
    public ResponseEntity<?> productsByCategory(@RequestBody String category) throws Exception {
        try {
            List<Product> products = productService.productsByCategory(category);
            return ResponseEntity.ok().body(products);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Erro ao buscar produtos: " + e.getMessage());
        }
    }
    
    

}
