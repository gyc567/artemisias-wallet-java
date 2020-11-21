package com.artemisias.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
