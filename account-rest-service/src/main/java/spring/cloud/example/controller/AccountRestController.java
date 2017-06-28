package spring.cloud.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.example.service.Account;
import spring.cloud.example.service.AccountNotFoundException;
import spring.cloud.example.service.AccountService;

import java.util.List;


@Slf4j
@RestController
public class AccountRestController {

    @Autowired
    public AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping("/accounts/{accountNumber}")
    public Account byNumber(@PathVariable("accountNumber") String accountNumber) {

        log.info("accounts-service byNumber() invoked: " + accountNumber);
        Account account = accountService.findByNumber(accountNumber);
        log.info("accounts-service byNumber() found: " + account);

        if (account == null)
            throw new AccountNotFoundException(accountNumber);
        else {
            return account;
        }
    }

    @RequestMapping("/accounts/owner/{name}")
    public List<Account> byOwner(@PathVariable("name") String partialName) {
        log.info("accounts-service byOwner() invoked: "
                + accountService.getClass().getName() + " for "
                + partialName);

        List<Account> accounts = accountService.byOwnerContains(partialName);
        log.info("accounts-service byOwner() found: " + accounts);

        if (accounts == null || accounts.size() == 0)
            throw new AccountNotFoundException(partialName);
        else {
            return accounts;
        }
    }
}
