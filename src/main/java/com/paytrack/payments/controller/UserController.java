package com.paytrack.payments.controller;

import com.paytrack.payments.dto.UserDTO;
import com.paytrack.payments.service.UserService;
import com.paytrack.payments.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/user")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDTO user){
        return ResponseEntity.ok(userService.create(user));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getUser(@PathVariable String id){
        return ResponseEntity.ok(userService.get(id));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable String id, @Valid @RequestBody UserDTO user){
        return ResponseEntity.ok(userService.update(id, user));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable String id){
        return ResponseEntity.ok(userService.delete(id));
    }

    @GetMapping("/user")
    public ResponseEntity<Object> getAllUser(){
        return ResponseEntity.ok(userService.getAll());
    }


}
