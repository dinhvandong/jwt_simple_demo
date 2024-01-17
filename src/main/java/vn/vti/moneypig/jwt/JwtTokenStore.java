package vn.vti.moneypig.jwt;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenStore {


    private static JwtTokenStore instance;
    // Private constructor to prevent instantiation from outside the class
    public JwtTokenStore() {
    }
    // Method to get the singleton instance
    public static JwtTokenStore getInstance() {
        if (instance == null) {
            synchronized (JwtTokenStore.class) {
                if (instance == null) {
                    instance = new JwtTokenStore();
                }
            }
        }
        return instance;
    }
    private static final Map<String, String> tokenStore = new HashMap<>();

    public void storeToken(String username, String token) {
        tokenStore.put(username, token);
    }

    public void removeToken(String username) {
        tokenStore.remove(username);
    }

    public boolean isTokenPresent(String username, String token) {
        String storedToken = tokenStore.get(username);
        System.out.println("storedToken:"+ storedToken);
        System.out.println("token:"+ token);
        return storedToken != null && storedToken.equals(token);
    }
}