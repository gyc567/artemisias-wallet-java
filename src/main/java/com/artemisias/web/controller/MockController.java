package com.artemisias.web.controller;

import com.artemisias.web.model.Account;
import com.artemisias.web.model.Result;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/v1/public/mock")
public class MockController {

    @Resource
    private RedissonClient redissonClient;
    @Value("${javadevjournal.welcome.message}")
    private String welcomeMsg;

    /**
     * Our Welcome display message which will use the welcome message property injected through the
     *
     * @return welcome message
     * @Value annotation.welcome
     */
    @GetMapping("/welcome")
    public String displayWelcomeMsg() {
        return welcomeMsg;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> accountSummary() {
        ArrayList<Account> accounts = Lists.newArrayList();
        Account a = Account.builder()
                .entityId(1L)
                .name("Tom")
                .number("123").build();
        accounts.add(a);
        Result<Object> success = Result.success(accounts);
        return success;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> accounts() {
        ArrayList<Account> accounts = Lists.newArrayList();
        Account a = Account.builder()
                .entityId(1L)
                .name("Tom")
                .number("123").build();
        accounts.add(a);
        Result<Object> success = Result.success(accounts);
        return ResponseEntity.ok(accounts);
    }


    @GetMapping("/lock1")
    public String lock1() {
        // 1.获取分布式锁
        RLock lock = redissonClient.getLock("my:lock");
        // 2.进行加锁操作
        try {
            boolean b = lock.tryLock(0L, 2L, TimeUnit.SECONDS);
            System.out.println(LocalDateTime.now() + " lock1 locked:"+b);

            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        } finally {
            // 3.释放锁资源
            lock.unlock();
            System.out.println(LocalDateTime.now() + "lock1 unlocked");

        }
        return "lock1";
    }

    @GetMapping("/lock2")
    public String lock2() {
        RLock lock = redissonClient.getLock("my:lock");
        lock.lock();
        System.out.println(LocalDateTime.now() + " lock2 locked");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        lock.unlock();
        System.out.println(LocalDateTime.now() + " lock2 unlocked");
        return "lock2";
    }

}
