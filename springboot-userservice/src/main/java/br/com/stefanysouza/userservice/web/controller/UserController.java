package br.com.stefanysouza.userservice.web.controller;

import br.com.stefanysouza.userservice.database.entity.User;
import br.com.stefanysouza.userservice.service.UserService;
import br.com.stefanysouza.userservice.web.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody UserDTO userDTO) {
        userService.save(userDTO.toUser());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.listAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findById(@PathVariable String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByUserId(userId));
    }

    @GetMapping("/{userId}/{gameId}")
    public ResponseEntity<User> findByKey(@PathVariable String userId, @PathVariable String gameId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(userId, gameId));
    }


}
