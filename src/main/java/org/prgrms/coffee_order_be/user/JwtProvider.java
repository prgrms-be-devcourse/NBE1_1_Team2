package org.prgrms.coffee_order_be.user;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;

    private Key key;

    @PostConstruct
    public void init() {
        byte[] secretByte = secret.getBytes();
        this.key = Keys.hmacShaKeyFor(secretByte);

    }

    public String createToken(Map<String, Object> claims, Date expireDate) {
        return Jwts.builder()
                .claims().add(claims)
                .expiration(expireDate)
                .and()
                .signWith(key)
                .compact();
    }

    public Claims parseClaims(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | io.jsonwebtoken.MalformedJwtException e) {
            System.out.println("Invalid JWT signature.");
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            System.out.println("Expired JWT token.");
        } catch (io.jsonwebtoken.UnsupportedJwtException e) {
            System.out.println("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            System.out.println("JWT token compact of handler are invalid.");
        }
        return false;
    }
}
