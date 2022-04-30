package br.com.stefanysouza.userservice.database.repository;

import br.com.stefanysouza.userservice.database.entity.User;
import br.com.stefanysouza.userservice.database.entity.UserKey;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface UserRepository extends CrudRepository<User, UserKey> {
    Optional<User> findByUserId(String userId);
}
