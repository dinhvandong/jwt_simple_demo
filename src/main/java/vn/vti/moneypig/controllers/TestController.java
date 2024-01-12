package vn.vti.moneypig.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }
//    @GetMapping("/my-endpoint")
//    public String myEndpoint(HttpServletRequest request) {
//        // Extract the token from the request headers or any other location
//        String token = JWTFilter.getInstance().extractToken(request);
//        if (JWTFilter.getInstance().isValidToken(token)) {
//            // Token is valid, perform the desired action
//            return "Authorized";
//        } else {
//            // Token is invalid or missing, handle the error as per your application's requirements
//            return "Unauthorized";
//        }
//    }


}
