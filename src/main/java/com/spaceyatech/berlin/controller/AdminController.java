package com.spaceyatech.berlin.controller;

import com.spaceyatech.berlin.response.AllUsersResponse;
import com.spaceyatech.berlin.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/allusers")
    public List<AllUsersResponse> fetchAllUsers(){

        return userService.allUsers();

    }
}
