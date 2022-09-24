package com.entitylogic.accounts.controller;

import com.entitylogic.accounts.controller.response.GetAccountsResponse;
import com.entitylogic.accounts.dto.AccountDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@RefreshScope
@RequestMapping("/v1/accounts")
class AccountsController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${application.allow-get-accounts}")
    private boolean allowGetAccounts;

    @GetMapping
    public GetAccountsResponse getAccountsResponse(@RequestParam long customerId) {
        log.info("Get accounts for customerId = {}", customerId);
        if (!allowGetAccounts) {
            log.info("Getting accounts is disabled");
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Getting accounts is disabled");
        }
        System.out.println(discoveryClient.getServices());
        List<AccountDto> accountsList = List.of(new AccountDto(1, "123", "PLN", new BigDecimal(15000)));
        return GetAccountsResponse.of(accountsList);
    }
}
