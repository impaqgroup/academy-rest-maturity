package com.impaq.accounts.resources;

import com.impaq.accounts.Account;
import com.impaq.accounts.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/accounts")
public class AccountRestResource {

    private AccountRepository accountRepository;

    @Autowired
    public AccountRestResource(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Account add(@RequestBody Account input) {
        return accountRepository.save(input);
    }
}
