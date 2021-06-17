package com.demomongo.Chat.Repository;

import com.demomongo.Chat.Modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByName(String name);
    boolean existsByName(String name);
}
