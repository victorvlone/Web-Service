package com.webserive.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webserive.demo.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
