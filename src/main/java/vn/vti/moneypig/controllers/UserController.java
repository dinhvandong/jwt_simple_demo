package vn.vti.moneypig.controllers;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vti.moneypig.dto.ResponseObject;
import vn.vti.moneypig.jwt.JWTUtility;
import vn.vti.moneypig.jwt.JwtInterceptor;
import vn.vti.moneypig.jwt.JwtTokenStore;
import vn.vti.moneypig.models.User;
import vn.vti.moneypig.otp.OTPService;
import vn.vti.moneypig.services.FirebaseService;
import vn.vti.moneypig.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private  final UserService userService;
    private final JwtInterceptor jwtInterceptor;
    private final JwtTokenStore jwtTokenStore;
    private final FirebaseService firebaseService;
    private final OTPService otpService;
    @Autowired
    public UserController(UserService userService, JwtInterceptor jwtInterceptor,
                          JwtTokenStore jwtTokenStore, FirebaseService firebaseService, OTPService otpService) {
        this.userService = userService;
        this.jwtInterceptor = jwtInterceptor;
        this.jwtTokenStore = jwtTokenStore;
        this.firebaseService = firebaseService;
        this.otpService = otpService;
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
    @GetMapping("/new-access-token")
    public ResponseEntity<?> newAccessToken(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, userService.findAll(),"success"));
    }
    @DeleteMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        String username = jwtInterceptor.extractUsername(token);
        if (jwtTokenStore.isTokenPresent(username, token)) {
            jwtTokenStore.removeToken(username);
            return ResponseEntity.ok("Logged out successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid token");
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOTP(@RequestParam("idToken") String idToken) {
        try {
            FirebaseToken firebaseToken = firebaseService.verifyOTP(idToken);
            // Perform additional actions with the verified user's information if needed
            return ResponseEntity.ok("OTP verification successful");
        } catch (FirebaseAuthException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP");
        }
    }

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOTP(@RequestParam("phoneNumber") String phoneNumber) {
        otpService.sendOTP(phoneNumber);
        return ResponseEntity.ok("OTP sent successfully");
    }
}
