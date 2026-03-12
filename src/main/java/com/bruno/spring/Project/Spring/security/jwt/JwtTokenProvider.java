package com.bruno.spring.Project.Spring.security.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bruno.spring.Project.Spring.data.dto.security.TokenDTO;
import com.bruno.spring.Project.Spring.exceptions.InvalidJwtAutenticationExeception;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    @Value("${security.jwt.secret-key:secret}")
    private String secretKey;
    @Value("${security.jwtexpire-length:360000}")
    private long expireLenght;

    private UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenDTO createToken(String userName, List<String> roles){
        Date now = new Date();
        Date validity = new Date(now.getTime() + expireLenght);

        String accessToken = getAccessToken(userName, roles, now , validity);
        String refreshToken = getRefreshToken(userName, roles, now);
        return new TokenDTO(userName,true,now,validity,accessToken,refreshToken);
    }

    private String getRefreshToken(String userName, List<String> roles, Date now) {
        Date refreshTokenValidity = new Date(now.getTime() + (expireLenght * 3));
        return JWT
                .create()
                .withSubject(userName)
                .withClaim("roles",roles)
                .withIssuedAt(now)
                .sign(algorithm);
    }

        private String getAccessToken(String userName, List<String> roles, Date now, Date vality) {

        String issurUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return JWT
                .create()
                .withSubject(userName)
                .withClaim("roles",roles)
                .withIssuedAt(now)
                .withIssuer(issurUrl)
                .withExpiresAt(vality)
                .sign(algorithm);
    }

    public Authentication getAuthentication(String token){
        DecodedJWT decodedJWT = decodeDToken(token);
        UserDetails userDetails = this.userDetailsService
                .loadUserByUsername(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails,"", userDetails.getAuthorities());
    }

    private DecodedJWT decodeDToken(String token) {
        Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
        JWTVerifier verifier = JWT.require(alg).build();
        return verifier.verify(token);
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.isEmpty(bearerToken) && bearerToken.startsWith("Bearer")){
            return bearerToken.substring("Bearer".length());
        }else {
            throw new InvalidJwtAutenticationExeception("Invalid JWT Token");
        }
    }

    public boolean validToken(String token){
        DecodedJWT decodedJWT = decodeDToken(token);
        try {
            if (decodedJWT.getExpiresAt().before(new Date())){
                return false;
            }
            return true;
        } catch (Exception e) {
            throw new InvalidJwtAutenticationExeception("Expired or Invalid JWT Token");
        }
    }



}
