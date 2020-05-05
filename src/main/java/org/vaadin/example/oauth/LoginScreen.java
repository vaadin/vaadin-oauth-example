package org.vaadin.example.oauth;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.backend.UserSession;

/**
 * UI content when the user is not logged in yet.
 */
@Route("login")
@PageTitle("Login")
public class LoginScreen extends FlexLayout {

    private static final String URL = "/oauth2/authorization/google";

    @Autowired
    UserSession userSession;

    String redirectUrl;

    public LoginScreen() {
        Anchor gplusLoginButton = new Anchor(URL, "Login with Google");
        add(gplusLoginButton);
        setSizeFull();
    }
}