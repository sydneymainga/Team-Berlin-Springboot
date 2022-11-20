package com.spaceyatech.berlin.services;

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
import com.spaceyatech.berlin.utilities.Dry;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class UserService {
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

    public JwtResponse signIn(LoginRequest loginRequest){


        log.info("sign in request:-->{}",loginRequest);


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );


        log.info("authentication data:-->{}", authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String access_token = jwtUtils.generateJwtToken(authentication);
        String refresh_token = jwtUtils.generateJwtRefreshToken(authentication);


        UserDetailss userDetails = (UserDetailss) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());



        JwtResponse jwtResponse = JwtResponse.builder()
                .id(userDetails.getId())
                .email(userDetails.getEmail())
                .roles(roles)
                .access_token(access_token)
                .refresh_token(refresh_token)
                .type("Bearer")
                .username(userDetails.getUsername())
                .build();

        log.info("jwtResponse:-->{}", jwtResponse);

        return jwtResponse;

    }

    public MessageResponse signUp(SignUpRequest signUpRequest){



        try{
            log.info("signup request:-->{}",signUpRequest);

            if(userRepository.existsByUsername(signUpRequest.getUsername())) {
                return new MessageResponse("Error: Username is already taken!");
                /*return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Username is already taken!"));*/
            }

            if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                return new MessageResponse("Error: Email is already in use!");
                /*return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Email is already in use!"));*/
            }

            // Create new user's account
            String date = Dry.getCurrentDate();

            log.info("getcurrentdate:-->{}",date);

            User user = User.builder()
                    .username(signUpRequest.getUsername())
                    .email(signUpRequest.getEmail())
                    .password(encoder.encode(signUpRequest.getPassword()))
                    .date_created(Timestamp.valueOf(date))//change to proper date
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


        MessageResponse msg = MessageResponse.builder()
                .message("User registered successfully!")
                .build();
        log.info("Registered:-->{}",msg);


        return msg;

    }
}
