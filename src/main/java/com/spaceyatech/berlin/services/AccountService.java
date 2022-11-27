package com.spaceyatech.berlin.services;

import com.spaceyatech.berlin.interfaces.AccountInterface;
import com.spaceyatech.berlin.repository.AccountRepository;
import com.spaceyatech.berlin.requests.AccountRequest;
import com.spaceyatech.berlin.response.AccountResponse;
import com.spaceyatech.berlin.response.MessageResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class AccountService implements AccountInterface {

    private AccountRepository accountRepository;
    @Override
    public MessageResponse createAccount(AccountRequest accountRequest) {
        return null;
    }

    @Override
    public MessageResponse updateAccount(AccountRequest accountRequest) {
        return null;
    }

    @Override
    public AccountResponse fetchAllAccountsAssociatedToUserByUserId(UUID Id) {
       return null;
    }

    @Override
    public AccountResponse fetchAccountByAccountId(UUID id) {
       return null;
    }

    @Override
    public MessageResponse deleteAccountByAccountId(UUID id) {
        return null;
    }

}
