package com.webserive.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webserive.demo.DTO.ProductDTO;
import com.webserive.demo.entities.Category;
import com.webserive.demo.entities.Product;
import com.webserive.demo.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public Product newProduct(ProductDTO product) {
        Product newProduct = new Product();
        newProduct.setName(product.name());
        newProduct.setDescription(product.Description());
        newProduct.setPrice(product.Price());
        newProduct.setImgUrl(product.imgUrl());

        List<Category> categories = product.categories().stream()
                .map(categoryService::findCategory)
                .toList();

        newProduct.setCategories(categories);

        return productRepository.save(newProduct);
    }

    public Product deleteProduct(Integer id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return product;
                })
                .orElseThrow(() -> new RuntimeException("Produto com id " + id + " não encontrado!"));
    }

    public Product findProduct(String productName) {
        Optional<Product> product = productRepository.findByName(productName);
        return product.orElseThrow(
                () -> new RuntimeException("Produto '" + productName + "' não encontrado!"));
    }

    public List<Product> allProducts() throws Exception {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new Exception("Nenhum produto encontrado!");
        }
        return products;
    }

    public List<Product> productsByCategory(String category) throws Exception {
        List<Product> products = productRepository.findByCategories_Name(category);
        if(products.isEmpty()){
            throw new Exception("Nenhum produto encontrado nessa categoria!");
        }
        return products;
    }

}
