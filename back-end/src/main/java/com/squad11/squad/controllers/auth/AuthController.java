package com.squad11.squad.controllers.auth;

import com.squad11.squad.Utils.Dto.Auth.AuthRequest;
import com.squad11.squad.services.auth.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController()
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceImpl userService;

    @PostMapping("authentication")
    public ResponseEntity<Map<String,String>> authentication(@RequestBody AuthRequest authRequest){
        Map<String,String> response =   userService.authentication(authRequest);
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

   @PostMapping("register")
    public ResponseEntity<Map<String,String>> register(@RequestBody AuthRequest authRequest){
       Map<String,String> response = userService.register(authRequest);
       return  new ResponseEntity<>(response, HttpStatus.CREATED);
   }
}
