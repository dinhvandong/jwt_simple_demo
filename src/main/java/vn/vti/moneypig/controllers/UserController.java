package vn.vti.moneypig.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.vti.moneypig.dto.ResponseObject;
import vn.vti.moneypig.dto.UserDTO;

@RestController
@RequestMapping("/api/user")
public class UserController {


    @PostMapping("/find-user-by-token")
    public ResponseEntity<?> find_user_by_token()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, "null", "Username already exists"));

    }


}
