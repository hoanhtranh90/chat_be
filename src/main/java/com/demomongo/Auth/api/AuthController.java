package com.demomongo.Auth.api;


import com.demomongo.Auth.DTO.UserDto;
import com.demomongo.Auth.entity.Role;
import com.demomongo.Auth.entity.Token;
import com.demomongo.Auth.entity.User;
import com.demomongo.Auth.repository.RoleRepository;
import com.demomongo.Auth.repository.UserRepository;
import com.demomongo.Auth.security.JwtUtil;
import com.demomongo.Auth.security.UserPrincipal;
import com.demomongo.Auth.service.TokenService;
import com.demomongo.Auth.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashSet;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostConstruct
    public  ResponseEntity createFirstRole(){
        //
        Role role = new Role();
        role.setRoleName("USER");
        roleRepository.save(role);
        Role role1 = new Role();
        role1.setRoleName("ADMIN");
        roleRepository.save(role1);
        return ResponseEntity.ok("success");
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        Role role = roleRepository.findByRoleName("USER");
        HashSet role_ = new HashSet();
        role_.add(role);
        user.setRoles(role_);
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        UserPrincipal userPrincipal = userService.findByUsername(user.getUsername());
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

    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity testUser() {
        return ResponseEntity.ok("USER");
    }

    @GetMapping("/helloAdmin")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity testAdmin() {
        return ResponseEntity.ok("ADMIN");
    }

    @GetMapping("/getInfo")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity getUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());
        ModelMapper mapper = new ModelMapper();

        //convert Dto
        UserDto userDto = mapper.map(user, UserDto.class);


        return ResponseEntity.ok(userDto);
    }
}
