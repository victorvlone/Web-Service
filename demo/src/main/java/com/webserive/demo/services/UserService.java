package com.webserive.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webserive.demo.entities.User;
import com.webserive.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        User newUser = userRepository.save(user);
        return newUser;
    }

    public Optional<User> deleteUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        }
        return user;
    }

    public Optional<User> updateUser(User user) throws Exception {
        Optional<User> findUser = userRepository.findById(user.getId());
        if (findUser.isEmpty()) {
            throw new Exception("Usuario não encontrado!");
        }
        User updatedUser = userRepository.save(user);
        return Optional.of(updatedUser);
    }

}
