package br.com.stefanysouza.userservice.service.impl;

import br.com.stefanysouza.userservice.database.entity.User;
import br.com.stefanysouza.userservice.database.entity.UserKey;
import br.com.stefanysouza.userservice.database.repository.UserRepository;
import br.com.stefanysouza.userservice.service.UserService;
import br.com.stefanysouza.userservice.web.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> listAll() {
        log.info("Listing all users...");
        Iterable<User> users = userRepository.findAll();
        return StreamSupport
                .stream(users.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public User findByUserId(final String userId) {
        log.info("Searching user with user_id {}", userId);
        Optional<User> userOptional = userRepository.findByUserId(userId);

        final String notFoundMessage = String.format("User with user_id %s not found", userId);
        return userOptional.orElseThrow(() -> new NotFoundException(notFoundMessage));
    }

    @Override
    public User findById(final String userId, final String gameId) {
        log.info("Searching user with user_id {} and game_id {}", userId, gameId);
        Optional<User> userOptional = userRepository.findById(new UserKey(userId, gameId));

        final String notFoundMessage = String.format("User with user_id %s and game_id %s not found", userId, gameId);
        return userOptional.orElseThrow(() -> new NotFoundException(notFoundMessage));
    }

    @Override
    public void save(final User user) {
        log.info("Saving user in database");

        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);

        log.info("User successfully saved!");
    }
}
