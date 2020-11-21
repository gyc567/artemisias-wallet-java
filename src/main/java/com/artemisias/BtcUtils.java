package com.artemisias;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Utils;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.script.Script;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.Wallet;
import org.bitcoinj.core.listeners.DownloadProgressTracker;
import org.bitcoinj.core.*;
import org.bitcoinj.net.discovery.DnsDiscovery;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.store.SPVBlockStore;
import org.bitcoinj.wallet.UnreadableWalletException;

import java.io.File;

public class BtcUtils {
    public static String getMnemonicCodeWord() {
        // NetworkParameters params = TestNet3Params.get();
        NetworkParameters params = NetworkEnum.TEST.get();

        Wallet wallet = Wallet.createDeterministic(params, Script.ScriptType.P2PKH);

        DeterministicSeed seed = wallet.getKeyChainSeed();
        System.out.println("seed: " + seed.toString());

        System.out.println("creation time: " + seed.getCreationTimeSeconds());
        System.out.println("MnemonicCode: " + seed.getMnemonicCode());
        String join = Utils.SPACE_JOINER.join(seed.getMnemonicCode());
        System.out.println("mnemonicCode: " + join);
        return join;
    }

    public static String restoreFromSeed(String seedCode, Long birthday)
            throws InterruptedException, BlockStoreException, UnreadableWalletException {
        NetworkParameters params = TestNet3Params.get();

        // Bitcoinj supports hierarchical deterministic wallets (or "HD Wallets"):
        // https://github.com/bitcoin/bips/blob/master/bip-0032.mediawiki
        // HD wallets allow you to restore your wallet simply from a root seed. This
        // seed can be represented using a short mnemonic sentence as described in BIP
        // 39: https://github.com/bitcoin/bips/blob/master/bip-0039.mediawiki

        // Here we restore our wallet from a seed with no passphrase. Also have a look
        // at the BackupToMnemonicSeed.java example that shows how to backup a wallet by
        // creating a mnemonic sentence.
        // String seedCode = "yard impulse luxury drive today throw farm pepper survey
        // wreck glass federal";
        String passphrase = "";
        // Long creationtime = 1409478661L;
        // Long creationtime = System.currentTimeMillis();

        DeterministicSeed seed = new DeterministicSeed(seedCode, null, passphrase, birthday);

        // The wallet class provides a easy fromSeed() function that loads a new wallet
        // from a given seed.
        Wallet wallet = Wallet.fromSeed(params, seed, Script.ScriptType.P2PKH);

        // Because we are importing an existing wallet which might already have
        // transactions we must re-download the blockchain to make the wallet picks up
        // these transactions
        // You can find some information about this in the guides:
        // https://bitcoinj.github.io/working-with-the-wallet#setup
        // To do this we clear the transactions of the wallet and delete a possible
        // existing blockchain file before we download the blockchain again further
        // down.
        String walletAddress = wallet.toString();
        System.out.println(walletAddress);
        wallet.clearTransactions(0);
        File chainFile = new File("restore-from-seed.spvchain");
        if (chainFile.exists()) {
            chainFile.delete();
        }

        // Setting up the BlochChain, the BlocksStore and connecting to the network.
        SPVBlockStore chainStore = new SPVBlockStore(params, chainFile);
        BlockChain chain = new BlockChain(params, chainStore);
        PeerGroup peerGroup = new PeerGroup(params, chain);
        peerGroup.addPeerDiscovery(new DnsDiscovery(params));

        // Now we need to hook the wallet up to the blockchain and the peers. This
        // registers event listeners that notify our wallet about new transactions.
        chain.addWallet(wallet);
        peerGroup.addWallet(wallet);

        DownloadProgressTracker bListener = new DownloadProgressTracker() {
            @Override
            public void doneDownload() {
                System.out.println("blockchain downloaded");
            }
        };

        // Now we re-download the blockchain. This replays the chain into the wallet.
        // Once this is completed our wallet should know of all its transactions and
        // print the correct balance.
        peerGroup.start();
        peerGroup.startBlockChainDownload(bListener);

        bListener.await();

        // Print a debug message with the details about the wallet. The correct balance
        // should now be displayed.
        System.out.println(walletAddress);

        // shutting down again
        peerGroup.stop();
        return walletAddress;
    }
}
