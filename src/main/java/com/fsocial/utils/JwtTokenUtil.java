package com.fsocial.utils;

import com.fsocial.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.InvalidParameterException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {
    @Value("${jwt.expiration}")
    private int expiration;
    @Value("${jwt.secretKey}")
    private String secretKey;

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole().getName());
        try {
            String token = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                    .subject(user.getEmail())
                    .issuedAt(new Date())
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();
            return token;
        } catch (Exception e) {
            throw new InvalidParameterException("Cannot create jwt token, error: " + e.getMessage());
        }

    }
    private SecretKey getSignInKey() {
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimsResolver.apply(claims);
    }
    public boolean isTokenExpired(String token) {
        Date expiration = getClaimFromToken(token, Claims::getExpiration);
        return expiration.before(new Date());
    }
    public String getEmailFromToken(String token) {
        return getClaimFromToken(token,Claims::getSubject);
    }
    public boolean validateToken(String token, UserDetails userDetails) {
        String email = getEmailFromToken(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
//    private static String generatrSecretKey() {
//        SecureRandom random = new SecureRandom();
//        byte[] bytes = new byte[32];
//        random.nextBytes(bytes);
//        String sceretKey = Encoders.BASE64.encode(bytes);
//        return sceretKey;
//        }
}
