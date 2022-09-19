package com.yeskela.phonebook.controller;

import com.yeskela.phonebook.dto.UserReq;
import com.yeskela.phonebook.dto.UserResp;
import com.yeskela.phonebook.entity.UserEntity;
import com.yeskela.phonebook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("user")
@RestController
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("add")
    public UserResp addUser(@RequestBody UserReq request) {
        return userService.save(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("edit/{userId}")
    public UserResp editUser(@PathVariable String userId, @RequestBody UserReq request) {
        return userService.edit(userId, request);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("delete/{userId}")
    public UserResp deleteUser(@PathVariable String userId) {
        return userService.delete(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("list")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

}
