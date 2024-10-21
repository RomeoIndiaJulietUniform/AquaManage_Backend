package com.DragonFish.aquaManage.UserService.Utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class JwtUtil {

    // Secret key used to sign and validate JWT tokens, generated at runtime
    private final String SECRET_KEY = generateSecretKey(32);

    /**
     * Generates a secret key of the specified length.
     * A SecureRandom instance ensures cryptographically strong key generation.
     *
     * @param length the desired length of the secret key in bytes
     * @return a base64 encoded string of the generated key
     */
    public static String generateSecretKey(int length) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[length]; // Generate a byte array of the specified length
        secureRandom.nextBytes(key); // Populate the byte array with random values
        return Base64.getEncoder().encodeToString(key); // Convert byte array to base64 string
    }

    /**
     * Generates a JWT token for the given username.
     *
     * @param username the username to be included in the JWT
     * @return the signed JWT token as a string
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // Set the username as the subject
                .setIssuedAt(new Date()) // Set the issue time to the current time
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Set expiration to 10 hours
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Sign with HS256 algorithm and secret key
                .compact(); // Generate and return the token
    }

    /**
     * Extracts claims from the provided JWT token.
     *
     * @param token the JWT token
     * @return the Claims object containing token data like subject, expiration, etc.
     */
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY) // Use the secret key to parse the token
                .parseClaimsJws(token) // Parse and validate the token
                .getBody(); // Return the claims from the token
    }

    /**
     * Extracts the username (subject) from the JWT token.
     *
     * @param token the JWT token
     * @return the username contained in the token
     */
    public String extractUsername(String token) {
        return extractClaims(token).getSubject(); // Get the subject (username) from the token's claims
    }

    /**
     * Checks if the JWT token has expired.
     *
     * @param token the JWT token
     * @return true if the token is expired, false otherwise
     */
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date()); // Check expiration date against current date
    }

    /**
     * Validates the JWT token by checking if the username matches and the token is not expired.
     *
     * @param token the JWT token
     * @param username the username to validate against the token
     * @return true if the token is valid, false otherwise
     */
    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token)); // Compare username and expiration status
    }
}
