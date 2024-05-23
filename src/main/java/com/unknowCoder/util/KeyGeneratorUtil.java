package com.unknowCoder.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class KeyGeneratorUtil {

    /**
     * Generates an RSA key pair using Java's cryptographic libraries.
     * @return KeyPair containing both the public and private RSA keys.
     * @throws NoSuchAlgorithmException if the RSA algorithm is not available.
     */
    public static KeyPair generateRsaKey() throws NoSuchAlgorithmException {
        try {
            // Create a KeyPairGenerator instance for the RSA algorithm.
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

            // Initialize the KeyPairGenerator with a key size of 2048 bits.
            keyPairGenerator.initialize(2048);

            // Generate the RSA KeyPair
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            // Rethrow the exception if RSA is not supported by the security provider.
            throw e;
        }
    }

}

