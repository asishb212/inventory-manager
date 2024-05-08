package Backend;

import java.security.SecureRandom;
import java.util.Base64;

public class SecurityConfig {
    public static String generatePepper() {
        SecureRandom random = new SecureRandom();
        byte[] pepper = new byte[16]; // 128 bits are considered secure
        random.nextBytes(pepper);
        return Base64.getEncoder().encodeToString(pepper);
    }

    public static void main(String[] args) {
        String pepper = generatePepper();
        System.out.println("Pepper: " + pepper);
        // Store this pepper securely


        // +loLd7uanqy34gzNWGu87g==
    }
}
