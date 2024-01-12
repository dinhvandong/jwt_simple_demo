package vn.vti.moneypig.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.vti.moneypig.dto.ResponseObject;
import vn.vti.moneypig.dto.UserDTO;
import vn.vti.moneypig.models.User;
import vn.vti.moneypig.repositories.UserRepository;
import vn.vti.moneypig.security.PasswordEncoder;
import vn.vti.moneypig.services.AuthService;
import vn.vti.moneypig.services.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserDTO userDTO) {
        if (userService.findByUsername(userDTO.getUsername())!=null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }
        System.out.println("userDTOString:"+ userDTO.toString());
        User requestUser = new User();
        requestUser.setId(1L);
        requestUser.setEmail("dongvan.ict3@gmail.com");
        requestUser.setPassword("123124Adasdasd@");
        requestUser.setPhone("0931232112");
        requestUser.setGoogleId("IT03123");
        requestUser.setUsername("dongvan.ict");
        requestUser.setStatus(1);

        User user = userRepository.insert(requestUser);

        System.out.println("userString:"+ user.toString());

      //  String token = authService.loginWithUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body
                (new ResponseObject(200, user, "success"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody UserDTO userDTO) {
        User user = userService.findByUsername((userDTO.getUsername()));
        if (user == null || !PasswordEncoder.getInstance().matches(userDTO.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(201, user, "success"));
        }
        String token = authService.loginWithUsernameAndPassword(user.getUsername(), user.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body
                (new ResponseObject(200,user,token));
    }
}
