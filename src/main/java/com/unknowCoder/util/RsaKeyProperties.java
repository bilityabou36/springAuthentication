package com.unknowCoder.util;

import org.springframework.stereotype.Component;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
public class RsaKeyProperties {
    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    public RsaKeyProperties() {
        // Constructor is left empty for better error handling and initialization control
    }

    private void generateKeys() {
        try {
            KeyPair pair = KeyGeneratorUtil.generateRsaKey();
            this.publicKey = (RSAPublicKey) pair.getPublic();
            this.privateKey = (RSAPrivateKey) pair.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            // Log this exception or handle it according to your error handling policy
            throw new RuntimeException("Failed to generate RSA keys", e);
        }
    }

    public RSAPublicKey getPublicKey() {
        if (publicKey == null) {
            generateKeys();
        }
        return publicKey;
    }

    public void setPublicKey(RSAPublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public RSAPrivateKey getPrivateKey() {
        if (privateKey == null) {
            generateKeys();
        }
        return privateKey;
    }

    public void setPrivateKey(RSAPrivateKey privateKey) {
        this.privateKey = privateKey;
    }
}

