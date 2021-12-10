package com.vinnotech.portal.repository;
import com.vinnotech.portal.model.User;
import org.springframework.data.repository.CrudRepository;
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}