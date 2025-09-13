package com.gl.app.controller;

import com.gl.app.dto.UserDto;
import com.gl.app.entity.User;
import com.gl.app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.addNewUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@Valid @PathVariable Long id){
        return new ResponseEntity<>(userService.findByUserId(id),HttpStatus.OK);
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existsByUserId(@Valid @PathVariable Long id){
        return new ResponseEntity<>(userService.existsByUserId(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@Valid @PathVariable Long id){
        return new ResponseEntity<>(userService.deleteUser(id),HttpStatus.OK);
    }
}


