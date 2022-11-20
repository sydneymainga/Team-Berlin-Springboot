package com.spaceyatech.berlin.controller;


import com.spaceyatech.berlin.requests.LoginRequest;
import com.spaceyatech.berlin.requests.SignUpRequest;
import com.spaceyatech.berlin.requests.TokenRefreshRequest;
import com.spaceyatech.berlin.response.JwtResponse;
import com.spaceyatech.berlin.response.MessageResponse;
import com.spaceyatech.berlin.response.TokenRefreshResponse;
import com.spaceyatech.berlin.services.UserService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@NoArgsConstructor

@Slf4j
public class AuthController {


    @Autowired
    private UserService userService;




    @PostMapping("/signin")
    public JwtResponse login( @Valid @RequestBody LoginRequest loginRequest) {

        JwtResponse response = userService.signIn(loginRequest);

        return response;


    }

    @PostMapping("/signup")
    public MessageResponse Register(@RequestBody @Valid SignUpRequest signUpRequest) {

        return userService.signUp(signUpRequest);


    }

    @PostMapping("/refresh-token")
    public TokenRefreshResponse RefreshToken(@RequestBody @Valid TokenRefreshRequest tokenRefreshRequest) {

        log.info(" RefreshToken to generate new access token:-->{}",tokenRefreshRequest);

        return userService.genarateRefreshToken(tokenRefreshRequest);


    }



}
