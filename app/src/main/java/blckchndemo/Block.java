package blckchndemo;

import java.util.Arrays;
import java.util.Date;

public class Block {
    private String hash;
    private String previousHash;
    private String[] transactions;
    private long timeStamp;
    private int nonce;


    public Block(String[] transactions, String previousHash) {
        this.transactions = transactions;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateBlockHash();
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String[] getTransactions() {
        return transactions;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String calculateBlockHash() {
        return StringUtils.applySha256(previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + Arrays.toString(transactions));
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while(!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateBlockHash();
        }
        System.out.println(StringUtils.getCurrentTime() + "Block mined: " + hash);
    }
}
