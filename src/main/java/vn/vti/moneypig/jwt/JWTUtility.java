package vn.vti.moneypig.jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtility {
    @Value("${jwt.secret}")
    private  String secret;
    @Value("${jwt.expirationMs}")
    private  long expirationMs;

    private static JWTUtility instance;

    // Private constructor to prevent instantiation from outside the class
    private JWTUtility() {
    }

    // Method to get the singleton instance
    public static JWTUtility getInstance() {
        if (instance == null) {
            synchronized (JWTUtility.class) {
                if (instance == null) {
                    instance = new JWTUtility();
                }
            }
        }
        return instance;
    }
    public  String generateToken(String username, String role) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMs);

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(),SignatureAlgorithm.HS512)
                .compact();
    }
    private  Key getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public  Claims parseToken(String token) {
       // SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(instance.secret));
        Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}