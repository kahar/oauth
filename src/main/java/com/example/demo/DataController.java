package com.example.demo;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class DataController {

    @GetMapping("/authentication")
    public Authentication getMyPrincipal(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/principal")
    public Principal getMyPrincipal(Principal principal) {
        return principal;
    }

    @GetMapping("/principal/name")
    public String getMyPrincipalName(Principal principal) {
        return principal.getName();
    }
}
