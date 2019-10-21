package com.example.demo.config.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;

@Configuration
@PropertySource("application-oauth2.properties")
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/console/**").permitAll()
                .antMatchers("/oauth_login", "/loginFailure", "/")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .loginPage("/oauth_login")
                .authorizationEndpoint()
                .baseUri("/oauth2/authorize-client")
                .authorizationRequestRepository(authorizationRequestRepository())
                .and()
                .tokenEndpoint()
                .accessTokenResponseClient(accessTokenResponseClient())
                .and()
                .defaultSuccessUrl("/loginSuccess")
                .failureUrl("/loginFailure");

        /*TODO ensure that belows options will be only for development with h2*/
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    private AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository() {
        return new HttpSessionOAuth2AuthorizationRequestRepository();
    }

    @Bean
    private OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {
        return new DefaultAuthorizationCodeTokenResponseClient();
    }

    @Bean
    public FilterRegistrationBean<AdditionalConfirmationFilter> loggingFilter() {
        FilterRegistrationBean<AdditionalConfirmationFilter> registrationBean
                = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdditionalConfirmationFilter());
        return registrationBean;
    }

}
