package com.vaadin.example;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


/**
 * Configures Spring Security using VaadinWebSecurity helper.
 * <br><br>
 *
 * VaadinWebSecurity provides basic Vaadin security
 * configuration for the project out of the box. It sets up security rules for a
 * Vaadin application and restricts all URLs except for public resources and
 * internal Vaadin URLs to authenticated user.<br><br>
 *
 * In this class, we only need to alter the {@code HttpSecurity}
 * configuration in order to configure authentication support using an OAuth 2.0.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends VaadinWebSecurity {

    private static final String LOGIN_URL = "/login";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.oauth2Login(c -> c.loginPage(LOGIN_URL).permitAll());
    }

}
