package br.com.stefanysouza.userservice.web.controller;

import br.com.stefanysouza.userservice.database.entity.User;
import br.com.stefanysouza.userservice.service.UserService;
import br.com.stefanysouza.userservice.web.dto.UserRequestDTO;
import br.com.stefanysouza.userservice.web.dto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid UserRequestDTO userDTO) {
        userService.save(userDTO.toUser());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        final List<User> users = userService.listAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(UserResponseDTO.toUserResponse(users));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable String userId) {
        final User user = userService.findByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(UserResponseDTO.toUserResponse(user));
    }

    @GetMapping("/{userId}/{gameId}")
    public ResponseEntity<UserResponseDTO> findByPrimaryKey(@PathVariable String userId, @PathVariable String gameId) {
        final User user = userService.findById(userId, gameId);
        return ResponseEntity.status(HttpStatus.OK).body(UserResponseDTO.toUserResponse(user));
    }
}
