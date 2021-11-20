package hashing;

import org.mockito.internal.util.StringUtil;

import java.util.Date;

public class Block {
    private String hashMethod;
    public String hash;
    public String previousHash;
    private String[] data;
    private long timeStamp;
    private int nonce;

    public Block(String[] data,String previousHash, String hashMethod, int nonce)
    {
        this.data = data;
        this.nonce = nonce;
        this.previousHash = previousHash;
        this.hashMethod = hashMethod;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash()
    {
        String calculatedhash = null;

        switch (hashMethod){
            case "SHA1":
                Hashing.encryptSHA1(previousHash+Long.toString(timeStamp)+Integer.toString(nonce) + String.join("|", data) );
                break;
            case "SHA2":
                Hashing.encryptSHA2(previousHash+Long.toString(timeStamp)+Integer.toString(nonce) + String.join("|", data) );
                break;
            case "SHA3":
                Hashing.encryptSHA3(previousHash+Long.toString(timeStamp)+Integer.toString(nonce) + String.join("|", data) );
                break;
            default:
                Hashing.encryptMD5(previousHash+Long.toString(timeStamp)+Integer.toString(nonce) + String.join("|", data) );
                break;
        }
        return calculatedhash;
    }
}
