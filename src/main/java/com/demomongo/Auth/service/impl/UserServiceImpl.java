package com.demomongo.Auth.service.impl;


import com.demomongo.Auth.entity.Token;
import com.demomongo.Auth.entity.User;
import com.demomongo.Auth.repository.UserRepository;
import com.demomongo.Auth.security.JwtUtil;
import com.demomongo.Auth.security.UserPrincipal;
import com.demomongo.Auth.service.TokenService;
import com.demomongo.Auth.service.UserService;

import com.demomongo.Chat.Modal.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenService tokenService;

    @Override
    public User createUser(User user) {
        return userRepository.saveAndFlush(user);
    }
    private User user;


    public User getUser(){
        return this.user;
    }
    @Override
    public UserPrincipal findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        UserPrincipal userPrincipal = new UserPrincipal();
        if (null != user) {
            Set<String> authorities = new HashSet<>();
            if (null != user.getRoles()) user.getRoles().forEach(r -> {
                authorities.add(r.getRoleName());
            });

            userPrincipal.setUserId(user.getId());
            userPrincipal.setUsername(user.getUsername());
            userPrincipal.setPassword(user.getPassword());
            userPrincipal.setAuthorities(authorities);
        }
        return userPrincipal;
    }

    @Override
    public List<Room> roomOfUser() {
        return null;
    }

    @Override
    public ResponseEntity<?> login(User user) {
        this.user = userRepository.findByUsername(user.getUsername());
        UserPrincipal userPrincipal = findByUsername(user.getUsername());
        if (null == user || !new BCryptPasswordEncoder().matches(user.getPassword(), userPrincipal.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("tài khoản hoặc mật khẩu không chính xác");
        }
        Token token = new Token();
        token.setToken(jwtUtil.generateToken(userPrincipal));
        token.setTokenExpDate(jwtUtil.generateExpirationDate());
        token.setCreatedBy(userPrincipal.getUserId());
        tokenService.createToken(token);
        return ResponseEntity.ok(token.getToken());
    }

}
