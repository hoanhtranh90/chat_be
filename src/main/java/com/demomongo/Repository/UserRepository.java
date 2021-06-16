package com.demomongo.Repository;

import com.demomongo.Modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByName(String name);
    boolean existsByName(String name);
}
