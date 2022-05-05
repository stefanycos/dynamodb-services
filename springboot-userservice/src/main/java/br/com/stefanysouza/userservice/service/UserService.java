package br.com.stefanysouza.userservice.service;

import br.com.stefanysouza.userservice.database.entity.User;

import java.util.List;

public interface UserService {

    List<User> listAll();

    User findByUserId(final String userId);

    User findById(final String userId, final String gameId);

    void save(final User user);
}
