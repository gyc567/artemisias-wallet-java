package com.ericguo;

import static org.junit.Assert.assertTrue;

import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

/**
 * Unit test for simple App.
 */
public class BtcUtilsTest {

    @Test
    public void getMnemonicCodeWordTest() {
        assertNotNull(BtcUtils.getMnemonicCodeWord());
    }

    @Test
    public void testRestoreFromSeed() {
        // String seedCode = "yard impulse luxury drive today throw farm pepper survey
        // wreck glass federal";
        String seedCode = "wide power fold flame question chunk ready harbor medal olympic latin elevator";
        Long birthday = 1580354278L;
        try {
            assertNotNull(BtcUtils.restoreFromSeed(seedCode, birthday));
        } catch (InterruptedException | BlockStoreException | UnreadableWalletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
