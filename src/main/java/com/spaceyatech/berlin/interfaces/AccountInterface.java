package com.spaceyatech.berlin.interfaces;

import com.spaceyatech.berlin.requests.AccountRequest;
import com.spaceyatech.berlin.response.AccountResponse;
import com.spaceyatech.berlin.response.MessageResponse;

import java.util.UUID;

public interface AccountInterface {

    public MessageResponse createAccount(AccountRequest accountRequest);
    public MessageResponse updateAccount(AccountRequest accountRequest);
    public AccountResponse fetchAllAccountsAssociatedToUserByUserId(UUID id);
    public AccountResponse fetchAccountByAccountId(UUID id);
    public MessageResponse deleteAccountByAccountId(UUID id);

}
