package vn.vti.moneypig.jwt;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JWTUtility {
   final long expirationMs = 24 * 60 * 60 * 1000; // One day
   final String secret = "AGIUPVIEC38271980371248973242139847231047238473294712039487321948703";
  final SecretKey secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS512.getJcaName());
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
    //generate token for user
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMs);
            return Jwts.builder()
                    .setSubject(username).setIssuedAt(now)
                    .setExpiration(expiryDate)
                    .signWith(secretKey)
                    .compact();
    }
    public  Claims parseToken(String token) {

        String tokenFix = token.replace("Bearer ", "");
        // Generate the secret key for HS512
        // Parse the JWT token
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(tokenFix)
                .getBody();
    }
    public String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Remove "Bearer " prefix
        }
        return null;
    }
    public String extractUsername(String token) {
        return parseToken(token).getSubject();
    }
//    public  String extractUsername(String token) {
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(secretKey)
//                .build().parseClaimsJws(token).getBody();
//        return claims.getSubject();
//    }
}