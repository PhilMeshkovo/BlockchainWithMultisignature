package phil.blockchain.example.start;

import phil.blockchain.example.Blockchain;
import phil.blockchain.example.Transaction;
import phil.blockchain.example.User;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();

        // Регистрация пользователей
        User user1 = new User("Alice");
        User user2 = new User("Bob");
        blockchain.addUser(user1);
        blockchain.addUser(user2);

        // Создание транзакции
        Transaction transaction1 = new Transaction(user1.getId(), user2.getId(), 50.0);
        transaction1.addSignature(user1.getId(), user1.signData(user1.getId() + user2.getId() + 50.0));
        transaction1.addSignature(user2.getId(), user2.signData(user1.getId() + user2.getId() + 50.0));

        List<Transaction> transactions1 = new ArrayList<>();
        transactions1.add(transaction1);

        // Добавление блока в блокчейн
        blockchain.addBlock(transactions1);

        // Печать блокчейна
        blockchain.printChain();
    }
}

