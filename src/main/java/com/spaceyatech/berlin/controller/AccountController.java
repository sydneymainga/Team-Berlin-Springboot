package com.spaceyatech.berlin.controller;

import com.spaceyatech.berlin.requests.AccountRequest;
import com.spaceyatech.berlin.response.MessageResponse;
import com.spaceyatech.berlin.services.AccountService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/Account")
@Tag(name="Account Controller")
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class AccountController {

    private AccountService accountService;

    @PostMapping("/createAccount")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<MessageResponse> createAccount(@RequestBody @Valid AccountRequest accountRequest){

        log.info("create account request: {}", accountRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(accountRequest));
    }

    @PutMapping("/updateAccount")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<MessageResponse> updateAccount(@RequestBody @Valid AccountRequest accountRequest){

        log.info("update account request: {}", accountRequest);
        return ResponseEntity.status(HttpStatus.OK).body(accountService.updateAccount(accountRequest));
    }

    @GetMapping("/findAccount/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> findAccountByAccountId(@PathVariable UUID id){

        log.info("account Id specified: -->{}",id);
        return ResponseEntity.status(HttpStatus.OK).body(accountService.fetchAccountByAccountId(id));
    }

    @GetMapping("/findAllUserAccounts/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> findAccountsAssociatedToUserByUserId(@PathVariable UUID id){
        log.info("user Id specified: -->{}",id);

        return ResponseEntity.status(HttpStatus.OK).body(accountService.fetchAllAccountsAssociatedToUserByUserId(id));
    }

    @DeleteMapping("/deleteAccount/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<MessageResponse> deleteAccountByAccountId(@PathVariable UUID id){

        log.info("account Id specified: -->{}",id);
        return ResponseEntity.status(HttpStatus.OK).body(accountService.deleteAccountByAccountId(id));
    }

}
