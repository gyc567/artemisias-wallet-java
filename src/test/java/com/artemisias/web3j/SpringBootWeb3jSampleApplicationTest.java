package com.artemisias.web3j;

import java.io.IOException;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootWeb3jSampleApplicationTest {

    @Autowired
    private Web3jSampleService web3jSampleService;

    // This test will only run if you provide a real Ethereum client for web3j to connect to
    @Test(expected = Exception.class)
    public void testGetClientVersion() throws IOException {
        assertThat(web3jSampleService.getClientVersion()).startsWith("Geth/");
    }
}