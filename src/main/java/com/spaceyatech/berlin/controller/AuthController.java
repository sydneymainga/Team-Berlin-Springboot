package com.spaceyatech.berlin.controller;


import com.spaceyatech.berlin.requests.LoginRequest;
import com.spaceyatech.berlin.requests.SignUpRequest;
import com.spaceyatech.berlin.requests.TokenRefreshRequest;
import com.spaceyatech.berlin.response.JwtResponse;
import com.spaceyatech.berlin.response.MessageResponse;
import com.spaceyatech.berlin.response.TokenRefreshResponse;
import com.spaceyatech.berlin.services.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @ResponseStatus( HttpStatus.ACCEPTED )
    @Tag(name="UserSignIn")
    public JwtResponse login( @Valid @RequestBody LoginRequest loginRequest) {

        JwtResponse response = userService.signIn(loginRequest);

        return response;


    }

    @PostMapping("/signup")
    @Tag(name="UserSignUp")
    @ResponseStatus( HttpStatus.CREATED )
    public MessageResponse Register(@RequestBody @Valid SignUpRequest signUpRequest) {

        return userService.signUp(signUpRequest);


    }

    @PostMapping("/refresh-token")
    @Tag(name="Renew Refresh Token")
    @ResponseStatus( HttpStatus.CREATED )
    public TokenRefreshResponse RefreshToken(@RequestBody @Valid TokenRefreshRequest tokenRefreshRequest) {

        log.info(" RefreshToken to generate new access token:-->{}",tokenRefreshRequest);

        return userService.genarateRefreshToken(tokenRefreshRequest);


    }



}
