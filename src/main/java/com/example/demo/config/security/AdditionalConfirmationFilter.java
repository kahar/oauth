package com.example.demo.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Component
/*TODO Why Order must be here with this value?*/
@Order(2)
public class AdditionalConfirmationFilter implements Filter {

    private final static Logger LOG = LoggerFactory.getLogger(AdditionalConfirmationFilter.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void init(final FilterConfig filterConfig) {
        LOG.info("Initializing filter :{}", this);
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getRequestURI();

        if ("/api/confirm".equals(url) || "/api/create".equals(url) || "/oauth_confirm".equals(url) || "/console".equals(url) || url.contains("/console/") || "/oauth_login".equals(url)) {
            chain.doFilter(request, response);
            return;
        } else {
            HttpSession session = req.getSession(false);

            SecurityContextImpl sci = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");

            if (sci != null) {
                OAuth2AuthenticationToken a = (OAuth2AuthenticationToken) sci.getAuthentication();
                String email = a.getPrincipal().getAttribute("email");
                Optional<User> result = userRepository.findById(email);
                if (result.isPresent()) {
                    User user = result.get();
                    if (user.isConfirmed()) {
                        chain.doFilter(request, response);
                        return;
                    }
                }
            }
        }
        res.sendRedirect("/oauth_confirm");

    }


    @Override
    public void destroy() {
        LOG.warn("Destructing filter :{}", this);
    }
}