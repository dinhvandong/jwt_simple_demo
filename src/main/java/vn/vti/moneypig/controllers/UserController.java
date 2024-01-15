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
import vn.vti.moneypig.services.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private  final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/findByToken")
    public ResponseEntity<?> findByToken(HttpServletRequest request) {
        String token = JwtInterceptor.getInstance().extractTokenFromRequest(request);
        if (token != null) {
            Claims claims =  JWTUtility.getInstance().parseToken(token);
            String username = claims.getSubject();
            if (username != null) {
                User user = userService.findByUsername(username);
                if (user != null) {
                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, user,"user exist"));
                }
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
//        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, userService.findAll(),"success"));
    }
    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam Long id){
        if(userService.findById(id) == null){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, userService.findById(id),"success"));
        }
    }
}
