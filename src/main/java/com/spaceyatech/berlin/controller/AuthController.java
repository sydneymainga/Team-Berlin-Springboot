package com.spaceyatech.berlin.controller;

import com.spaceyatech.berlin.requests.AuthLoginRequest;
import com.spaceyatech.berlin.requests.AuthSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class AuthController {

    /** https://www.bezkoder.com/spring-boot-jwt-authentication/ */
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody /*@Valid*/ AuthLoginRequest request) {

        return null;
    }

    @PostMapping("signup")
    public ResponseEntity<?> Register(@RequestBody /*@Valid*/ AuthSignUpRequest request) {

        return null;
    }


}
