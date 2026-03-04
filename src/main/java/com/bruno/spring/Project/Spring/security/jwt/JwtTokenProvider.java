package com.bruno.spring.Project.Spring.security.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bruno.spring.Project.Spring.data.dto.security.TokenDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.expire-length}")
    private long expireLenght;

    private UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenDTO createToken(String userName, List<String> roles){
        Date now = new Date();
        Date vality = new Date(now.getTime() + expireLenght);

        String accessToken = getAccessToken(userName, roles, now , vality);
        String refreshToken = getRefreshToken(userName, roles, now);
        return new TokenDTO(userName,true,now,vality,accessToken,refreshToken);
    }

    private String getRefreshToken(String userName, List<String> roles, Date now) {
        return "";
    }

    private String getAccessToken(String userName, List<String> roles, Date now, Date vality) {
        return JWT
                .create()
                .withSubject(userName)
                .withClaim("roles",roles)
                .withIssuedAt(now)
                .withExpiresAt(vality)
                .sign(algorithm);
    }


}
