package com.bridgelabz.AddressBookSpringBook.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bridgelabz.AddressBookSpringBook.exception.CustomException;
import org.springframework.stereotype.Component;

@Component
public class JWTToken {
    private final String SECRET="Sumit";
    public String createToken(Long id){
        String token;
        token= JWT.create().withClaim("id",id).sign(Algorithm.HMAC256(SECRET));
        return token;
    }
    public int decodeToken(String token){
        long id =0;
        if (token!=null){
            id =JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token).getClaim("id").asLong();

        }
        return (int) id;
    }

}
