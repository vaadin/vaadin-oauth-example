package com.vaadin.example;

import com.vaadin.flow.spring.security.VaadinAwareSecurityContextHolderStrategyConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static com.vaadin.flow.spring.security.VaadinSecurityConfigurer.vaadin;


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
@Import(VaadinAwareSecurityContextHolderStrategyConfiguration.class)
public class SecurityConfiguration {

    private static final String LOGIN_URL = "/login";

    @Bean
    public SecurityFilterChain vaadinSecurityFilterChain(HttpSecurity http) throws Exception {
        http.with(vaadin(), vaadin -> vaadin.oauth2LoginPage(LOGIN_URL));
        return http.build();
    }

}
