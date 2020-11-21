package com.artemisias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableFeignClients
@SpringBootApplication
//@EnableTransactionManagement
@EnableScheduling
@EnableAsync
public class ArtemisiasApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.artemisias.ArtemisiasApplication.class, args);
    }

}
