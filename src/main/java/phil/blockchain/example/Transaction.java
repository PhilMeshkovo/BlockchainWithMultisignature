package phil.blockchain.example;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class Transaction {
    private String sender;
    private String receiver;
    private double amount;
    private Map<String, byte[]> signatures;

    public Transaction(String sender, String receiver, double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.signatures = new HashMap<>();
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public double getAmount() {
        return amount;
    }

    public void addSignature(String userId, byte[] signature) {
        signatures.put(userId, signature);
    }

    public Map<String, byte[]> getSignatures() {
        return signatures;
    }

    public boolean verifySignatures(Map<String, PublicKey> publicKeys) {
        for (Map.Entry<String, byte[]> entry : signatures.entrySet()) {
            String userId = entry.getKey();
            byte[] signature = entry.getValue();
            PublicKey publicKey = publicKeys.get(userId);

            if (!CryptoUtil.verifySignature(sender + receiver + amount, signature, publicKey)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", amount=" + amount +
                ", signatures=" + signatures +
                '}';
    }
}

