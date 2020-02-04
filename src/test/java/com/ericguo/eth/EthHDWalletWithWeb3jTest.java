package com.ericguo.eth;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.junit.Test;
import org.web3j.crypto.CipherException;

/**
 * Unit test for simple App.
 */
public class EthHDWalletWithWeb3jTest {
    /**
     * Rigorous Test :-)
     * 
     * @throws IOException
     * @throws CipherException
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     */
    @Test
    public void testcreateWallet() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException,
            NoSuchProviderException, CipherException, IOException {
        assertNotNull(EthHDWalletWithWeb3j.createWallet());
    }
}
