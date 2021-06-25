package com.demomongo.Auth.api;

import com.demomongo.Auth.DTO.UserDto;
import com.demomongo.Auth.entity.User;
import com.demomongo.Auth.repository.UserRepository;
import com.demomongo.Auth.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getRoom")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity getUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());
        return ResponseEntity.ok(user.getRooms());
    }
}
