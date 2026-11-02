/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e_commerce_platform;

import java.security.SecureRandom;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author Rodmar
 */
public class Encryption {
    private static final int ITERATIONS = 1;
    private static final int KEY_LENGTH = 256;
    private static final int SALT_LENGTH = 16;

    public static String securePassword(String password) throws Exception {

        byte[] salt = new byte[SALT_LENGTH];

        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);

        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hashedPassword = factory.generateSecret(spec).getEncoded();
        spec.clearPassword();
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String encodedHash = Base64.getEncoder().encodeToString(hashedPassword);

        return ITERATIONS + ":" + encodedSalt + ":" + encodedHash;
    }

    public static boolean checkPassword(
            String password, String savedPassword) throws Exception {

        String[] data = savedPassword.split(":");

        if (data.length != 3) {
            return false;
        }

        int iterations = Integer.parseInt(data[0]);
        byte[] salt = Base64.getDecoder().decode(data[1]);
        byte[] savedHash = Base64.getDecoder().decode(data[2]);

        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, KEY_LENGTH);

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] newHash = factory.generateSecret(spec).getEncoded();
        spec.clearPassword();

        return MessageDigest.isEqual(savedHash, newHash);
    }
}
