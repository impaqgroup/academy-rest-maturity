package com.impaq.accounts.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.impaq.accounts.Account;
import com.impaq.accounts.AccountRepository;


@RestController
@RequestMapping("/accounts")
public class AccountRestResource {

    private AccountRepository accountRepository;

    @Autowired
    private AccountRestResource(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Account add(@RequestBody Account input) {
        return accountRepository.save(input);
    }
}
