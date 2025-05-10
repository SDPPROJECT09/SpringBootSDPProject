package com.klef.fsd.sdp.security;


import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;

@Service
public class JWTUtilities 
{

    public final String SECRET_KEY = "eyJhcGkta2V5IjogIlY0TkpDd0V5NFltZ1JEZDUwcHFlM2hkdmJaR0dqV1VuIn0=";
    public final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(String username, String role) 
    {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", encryptData(username));
        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 20000)) // 20 seconds)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Map<String, String> validateToken(String token) 
    {
        try 
        {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            Map<String, String> resp = new HashMap<>();
            resp.put("username", decryptData((String) claims.get("username")));
            resp.put("role", (String) claims.get("role"));
            resp.put("code", "200");
            return resp;

        } 
        catch (Exception e) 
        {
            Map<String, String> resp = new HashMap<>();
            resp.put("code", "404");
            resp.put("error", e.getMessage());
            return resp;
        }
    }

    public String encryptData(String data) 
    {
        try 
        {
            MessageDigest MD5 = MessageDigest.getInstance("SHA-256");
            byte[] keyBytes = MD5.digest("FSAD".getBytes());
            SecretKey cryptoKey = new SecretKeySpec(keyBytes, 0, 16, "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, cryptoKey);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        }
        catch (Exception e) 
        {
            return e.getMessage();
        }
    }

    public String decryptData(String data) 
    {
        try 
        {
            MessageDigest MD5 = MessageDigest.getInstance("SHA-256");
            byte[] keyBytes = MD5.digest("FSAD".getBytes());
            SecretKey cryptoKey = new SecretKeySpec(keyBytes, 0, 16, "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, cryptoKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(data));
            return new String(decryptedBytes);
        } 
        catch (Exception e) 
        {
            return e.getMessage();
        }
    }
}