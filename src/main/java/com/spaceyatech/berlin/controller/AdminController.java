package com.spaceyatech.berlin.controller;

import com.spaceyatech.berlin.models.User;
import com.spaceyatech.berlin.response.AllUsersResponse;
import com.spaceyatech.berlin.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping( "/allusers")
    @SecurityRequirement(name = "Bearer Authentication")
    @ResponseStatus( HttpStatus.FOUND )
    public List<AllUsersResponse> fetchAllUsers(){

        return userService.allUsers();


    }
}
