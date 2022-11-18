package com.spaceyatech.berlin.controller;

import com.spaceyatech.berlin.enums.RoleName;
import com.spaceyatech.berlin.models.Role;
import com.spaceyatech.berlin.models.User;
import com.spaceyatech.berlin.repository.RoleRepository;
import com.spaceyatech.berlin.repository.UserRepository;
import com.spaceyatech.berlin.requests.LoginRequest;
import com.spaceyatech.berlin.requests.SignUpRequest;
import com.spaceyatech.berlin.response.JwtResponse;
import com.spaceyatech.berlin.response.MessageResponse;
import com.spaceyatech.berlin.security.jwt.JwtUtils;
import com.spaceyatech.berlin.services.UserDetailss;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@NoArgsConstructor

@Slf4j
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtUtils jwtUtils;


    @PostMapping("/signin")
    public ResponseEntity<?> login( @Valid @RequestBody LoginRequest loginRequest) {


        log.info("sign in request:-->{}",loginRequest);


            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );


            log.info("authentication data:-->{}", authentication);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);


            UserDetailss userDetails = (UserDetailss) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());



            JwtResponse jwtResponse = JwtResponse.builder()
                    .id(userDetails.getId())
                    .email(userDetails.getEmail())
                    .roles(roles)
                    .token(jwt)
                    .type("Bearer")
                    .username(userDetails.getUsername())
                    .build();

            log.info("jwtResponse:-->{}", jwtResponse);


        return  ResponseEntity.ok( jwtResponse );
    }

    @PostMapping("/signup")
    public ResponseEntity<?> Register(@RequestBody @Valid SignUpRequest signUpRequest) {

        try{
        log.info("signup request:-->{}",signUpRequest);

        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account

        User user = User.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                //.date_created("2022-11-18 19:20:19")//change to proper date
                .build();

        log.info("user being saved:-->{}",user.toString());


        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(RoleName.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRole(roles);



            userRepository.save(user);
        }catch (Exception e){
            log.info("Error saving new user:-->{}",e.getMessage());
        }
       // userRepository.save(user);

        MessageResponse msg = MessageResponse.builder()
                .message("User registered successfully!")
                .build();
        log.info("Registered:-->{}",msg);

        return ResponseEntity.ok(msg);
    }



}
