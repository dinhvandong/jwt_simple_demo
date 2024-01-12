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
//    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AuthService(UserRepository userRepository) {
//        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public String loginWithUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null && PasswordEncoder.getInstance().matches(password, user.getPassword())) {
            return JWTUtility.getInstance().generateToken(username);
        }
        return null;
    }
}
