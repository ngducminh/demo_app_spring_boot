package com.example.app.controller;

import com.example.app.model.User;
import com.example.app.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Api(tags = "User Management")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    @ApiOperation("Get all users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("api/{id}")
    @ApiOperation("Get a user by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public User getUserById(@PathVariable("id") String id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping
    @ApiOperation("Create a new user")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("api/update/{id}")
    @ApiOperation("Update a user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User updated"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public User updateUser(@PathVariable("id") String id, @RequestBody User updatedUser) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());
            user.setEmail(updatedUser.getEmail());
            user.setPhone(updatedUser.getPhone());
            user.setGender(updatedUser.getGender());
            user.setBirthday(updatedUser.getBirthday());
            user.setDescription(updatedUser.getDescription());
            return userRepository.save(user);
        }
        return null;
    }

    @DeleteMapping("api/{id}")
    @ApiOperation("Delete a user")
    @ApiResponses({
            @ApiResponse(code = 204, message = "User deleted"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public void deleteUser(@PathVariable("id") String id) {
        userRepository.deleteById(id);
    }
}
