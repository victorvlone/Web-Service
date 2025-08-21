package com.webserive.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webserive.demo.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
