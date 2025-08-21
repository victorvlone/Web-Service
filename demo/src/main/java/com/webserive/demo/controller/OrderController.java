package com.webserive.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.webserive.demo.DTO.OrderDTO;
import com.webserive.demo.entities.Order;
import com.webserive.demo.services.OrderService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/new")
    public ResponseEntity<?> newOrder(@RequestBody OrderDTO dto) {
        try {
            Order newOrder = orderService.newOrder(dto);
            return ResponseEntity.status(201).body("Pedido criado: " + newOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Erro ao criar pedido: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Integer id) {
        try {
            Order order = orderService.deleteOrder(id);
            return ResponseEntity.status(200).body("pedido com ID: " + order.getId() + " removido com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("erro ao excluir pedido: " + e.getMessage());
        }
    }

}
