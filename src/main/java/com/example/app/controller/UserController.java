package com.example.app.controller;

import com.example.app.model.User;
import com.example.app.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Api(tags = "User API")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    @ApiOperation("Get all users")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "Thành công")
            ,
            @ApiResponse(code = 500, message = "Server Error")
            ,
            @ApiResponse(code = 401, message = "Chưa xác thực")
            ,
            @ApiResponse(code = 403, message = "Truy cập bị cấm")
            ,
            @ApiResponse(code = 404, message = "Không tìm thấy")
            ,
            @ApiResponse(code = 400, message = "Tham số không hợp lệ")
    })
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    @ApiOperation("Create a user")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

}
