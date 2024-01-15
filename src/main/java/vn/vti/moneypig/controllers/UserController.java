package vn.vti.moneypig.controllers;

import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vti.moneypig.dto.ResponseObject;
import vn.vti.moneypig.dto.UserDTO;
import vn.vti.moneypig.jwt.JWTUtility;
import vn.vti.moneypig.jwt.JwtInterceptor;
import vn.vti.moneypig.models.User;
import vn.vti.moneypig.repositories.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping("/findByToken")
    public ResponseEntity<?> findByToken(HttpServletRequest request) {
        String token = JwtInterceptor.getInstance().extractTokenFromRequest(request);
        System.out.println("TOKEN:ACD:"+ token);
        if (token != null) {
          //  String username = JwtInterceptor.getInstance().extractUsername(token);
            Claims claims =  JWTUtility.getInstance().parseToken(token);
            String username = claims.getSubject();
            if (username != null) {
                Optional<User> user = userRepository.findByUsername(username);
                if (user.isPresent()) {
                    return ResponseEntity.ok(user);
                }
            }
        }

        return ResponseEntity.notFound().build();
    }





}
