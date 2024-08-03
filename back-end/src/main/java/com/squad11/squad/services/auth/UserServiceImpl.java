package com.squad11.squad.services.auth;

import com.squad11.squad.Utils.Dto.Auth.AuthRequest;
import com.squad11.squad.config.Jwt.JwtService;
import com.squad11.squad.controllers.ControllerExceptionHandler.ResponseException;
import com.squad11.squad.persistence.entity.User;
import com.squad11.squad.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;
    private final JwtService jwtService;

    public Map<String, String> authentication(AuthRequest authRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(),
                authRequest.getPassword()
        ));


        //He extracts email from authentication.
        String jwt = jwtService.generateJwt(authentication.getName());
        return Map.of("JWT", jwt);
    }

    public Map<String, String> register(AuthRequest authRequest) {

        //Generate message email in used
        if (repository.findByEmail(authRequest.getEmail()).isPresent())
            throw new ResponseException("Email", "Email in used!", HttpStatus.OK);

        repository.save(generateUser(authRequest));

        String jwt = jwtService.generateJwt(authRequest.getEmail());

        return Map.of("JWT", jwt);
    }


    private User generateUser(AuthRequest authRequest) {
        return User.builder()
                .email(authRequest.getEmail())
                .password(encoder.encode(authRequest.getPassword()))
                .roles("ADMIN")
                .build();
    }


}
