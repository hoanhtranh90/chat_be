package com.demomongo.Auth.service;


import com.demomongo.Auth.entity.Token;

public interface TokenService {

    Token createToken(Token token);

    Token findByToken(String token);
}
