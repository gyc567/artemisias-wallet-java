package com.artemisias.web.controller;

import com.artemisias.web.model.Account;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/public/mock")
public class MockController {

    @Value("${javadevjournal.welcome.message}")
    private String welcomeMsg;

    /**
     * Our Welcome display message which will use the welcome message property injected through the
     * @Value annotation.welcome
     * @return welcome message
     */
    @GetMapping("/welcome")
    public String displayWelcomeMsg() {
        return welcomeMsg;
    }

    @RequestMapping(value="/orders", method= RequestMethod.GET)
    @ResponseBody
    public List<Account> accountSummary() {
        ArrayList<Account> accounts = Lists.newArrayList();
        Account a=Account.builder()
                .entityId(1L)
                .name("Tom")
                .number("123").build();
        accounts.add(a);
        return accounts;
    }
}
