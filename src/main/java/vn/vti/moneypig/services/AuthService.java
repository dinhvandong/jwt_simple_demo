package vn.vti.moneypig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.jwt.JWTUtility;
import vn.vti.moneypig.models.User;
import vn.vti.moneypig.repositories.UserRepository;
import vn.vti.moneypig.security.PasswordEncoder;

@Service
public class AuthService {
    private final UserRepository userRepository;
    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String loginWithUsernameAndPassword(String username, String password) {
        System.out.println("Username:"+ username);
        User user = userRepository.findByUsername(username).orElse(null);
        assert user != null;
        System.out.println("Test_3:"+ user.toString());
        if (PasswordEncoder.getInstance().matches(password, user.getPassword())) {
            System.out.println("OK_1");
            return JWTUtility.getInstance().generateToken(username);
        }
        return null;
    }
}
