package vn.vti.moneypig.jwt;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;
public class JWTUtility {
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    long expirationMs = 24 * 60 * 60 * 1000; // One day
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
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SECRET_KEY)
                .compact();
    }
    public  Claims parseToken(String token) {
        Jws<Claims> jws;
        try {
            jws = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
        } catch (Exception e) {
            // Handle invalid token or signature exception
            return null;
        }
        return jws.getBody();
    }
}