package com.example.demo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class DataController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/confirm")
    public void confirm(OAuth2AuthenticationToken authentication) {
        Optional<User> userO = userRepository.findById(authentication.getPrincipal().getAttribute("email"));
        if (userO.isPresent()) {
            User user = userO.get();
            user.setConfirmed(true);
            userRepository.save(user);
        }
    }

    @GetMapping("/create")
    public void create(OAuth2AuthenticationToken authentication) {
        Optional<User> userO = userRepository.findById(authentication.getPrincipal().getAttribute("email"));
        if (!userO.isPresent()) {
            User user = new User();
            user.setConfirmed(false);
            user.setGoogleMeil(authentication.getPrincipal().getAttribute("email"));
            userRepository.save(user);
        }
    }

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
