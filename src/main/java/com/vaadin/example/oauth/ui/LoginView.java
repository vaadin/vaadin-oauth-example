package com.vaadin.example.oauth.ui;


import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.core.env.Environment;

/**
 * Adds a link that the user has to click to login.
 *
 * This view is marked with {@code @AnonymousAllowed} to allow all users access
 * to the login page.
 */
@Route("login")
@PageTitle("Login")
@AnonymousAllowed
public class LoginView extends VerticalLayout {

    /**
     * URL that Spring uses to connect to Google services
     */
    private static final String OAUTH_URL = "/oauth2/authorization/google";

    public LoginView(@Autowired Environment env) {
        setPadding(true);
        setAlignItems(Alignment.CENTER);

        String clientkey = env.getProperty("spring.security.oauth2.client.registration.google.client-id");

        // Check that oauth keys are present
        if (clientkey == null || clientkey.isEmpty() || clientkey.length() < 32) {
            add(new Paragraph("Could not find OAuth client key in application.properties. "
                    + "Please double-check the key and refer to the README.md file for instructions."));
        } else {
            add(new H1("Login to access this app"));
            add(new Paragraph("This is demo app for Spring Security + Google, thus there is only one option to log in:"));
            Anchor loginLink = new Anchor(OAUTH_URL, "Login with Google");
            loginLink.addClassName(LumoUtility.FontSize.XLARGE);
            loginLink.setRouterIgnore(true); // actually navigate away from this app
            add(loginLink);
        }
    }
}
