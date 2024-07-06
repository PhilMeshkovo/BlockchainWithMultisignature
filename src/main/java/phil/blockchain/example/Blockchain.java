package phil.blockchain.example;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Blockchain {
    private List<Block> chain;
    private Map<String, PublicKey> publicKeys;

    public Blockchain() {
        this.chain = new ArrayList<>();
        this.publicKeys = new HashMap<>();
        // Создание генезис-блока
        chain.add(createGenesisBlock());
    }

    private Block createGenesisBlock() {
        return new Block(0, new ArrayList<>(), "0");
    }

    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    public void addBlock(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            if (!transaction.verifySignatures(publicKeys)) {
                throw new RuntimeException("Transaction signatures verification failed");
            }
        }

        Block previousBlock = getLatestBlock();
        Block newBlock = new Block(previousBlock.getIndex() + 1, transactions, previousBlock.getHash());
        chain.add(newBlock);
    }

    public void addUser(User user) {
        publicKeys.put(user.getId(), user.getPublicKey());
    }

    public List<Block> getChain() {
        return chain;
    }

    public void printChain() {
        for (Block block : chain) {
            System.out.println(block);
        }
    }
}

