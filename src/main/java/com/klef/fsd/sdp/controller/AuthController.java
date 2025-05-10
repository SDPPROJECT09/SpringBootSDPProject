package com.klef.fsd.sdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klef.fsd.sdp.security.JWTUtilities;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController 
{

    @Autowired
    JWTUtilities utilities;

    @GetMapping("/validate")
    public Map<String, String> validateToken(@RequestHeader("Authorization") String token) {
        return utilities.validateToken(token.replace("Bearer ", ""));
    }
}