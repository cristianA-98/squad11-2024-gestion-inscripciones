package com.squad11.squad.config.Jwt;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    private static String private_key = "2ns7utNmJSCwdrx6KZpVEqUFdyoHMJoqGXQR7328y2Fi4UL2ggCKS4d8ZRIw8QHL";
    private static long jwtExpirationDate = 3600000;

    //Create Token
    public String generateJwt(String email){

        Date curreDate = new Date();
        Date expirateDate = new Date(curreDate.getTime() + jwtExpirationDate);

        String token = Jwts.builder()
                .subject(email)
                .issuedAt(curreDate)
                .expiration(expirateDate)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();

        return  "Bearer " + token;

    }

    //Extract email from JWT
    public String extractJwt(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) getKey() )
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

    }

    // Validate token
    public boolean validateToken(String token){
        Jwts.parser()
                .verifyWith((SecretKey) getKey() )
                .build()
                .parse(token);
        return true;
    }


    private Key getKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(private_key));
    }
}
