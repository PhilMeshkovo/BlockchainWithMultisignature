package phil.blockchain.example;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.UUID;

public class User {
    private String id;
    private String name;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public User(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        KeyPair keyPair = CryptoUtil.generateKeyPair();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public byte[] signData(String data) {
        return CryptoUtil.signData(data, privateKey);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

