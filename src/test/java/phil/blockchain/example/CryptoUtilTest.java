package phil.blockchain.example;

import org.junit.Test;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

public class CryptoUtilTest {

    @Test
    public void testGenerateKeyPair() {
        KeyPair keyPair = CryptoUtil.generateKeyPair();
        assertNotNull("KeyPair should not be null", keyPair);
        assertNotNull("Private key should not be null", keyPair.getPrivate());
        assertNotNull("Public key should not be null", keyPair.getPublic());
    }

    @Test
    public void testSignAndVerifyData() {
        KeyPair keyPair = CryptoUtil.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        String data = "Hello, Blockchain!";
        byte[] signature = CryptoUtil.signData(data, privateKey);
        assertNotNull("Signature should not be null", signature);

        boolean isVerified = CryptoUtil.verifySignature(data, signature, publicKey);
        assertTrue("Signature should be verified", isVerified);
    }

    @Test
    public void testVerifyIncorrectSignature() {
        KeyPair keyPair1 = CryptoUtil.generateKeyPair();
        PublicKey publicKey1 = keyPair1.getPublic();

        KeyPair keyPair2 = CryptoUtil.generateKeyPair();
        PrivateKey privateKey2 = keyPair2.getPrivate();

        String data = "Hello, Blockchain!";
        byte[] signature = CryptoUtil.signData(data, privateKey2);

        boolean isVerified = CryptoUtil.verifySignature(data, signature, publicKey1);
        assertTrue("Signature should not be verified with incorrect public key", !isVerified);
    }
}

