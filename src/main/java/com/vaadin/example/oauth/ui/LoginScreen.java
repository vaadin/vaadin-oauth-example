package com.vaadin.example.oauth.ui;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * Adds an explicit link that the user has to click to login.
 */
@Route("login")
@PageTitle("Login")
public class LoginScreen extends FlexLayout {

    private static final String URL = "/oauth2/authorization/google";

    /**
     * This methods gets the user into google sign in page.
     */
    public LoginScreen() {
        Anchor gplusLoginButton = new Anchor(URL, "Login with Google");
        add(gplusLoginButton);
        setSizeFull();
    }
}