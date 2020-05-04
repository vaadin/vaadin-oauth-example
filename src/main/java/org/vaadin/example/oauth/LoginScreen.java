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

    @Autowired
    UserSession userSession;

    String redirectUrl;

    public LoginScreen() {
        String url = "/oauth2/authorization/google";
        Anchor gplusLoginButton = new Anchor(url, "Login with Google");
        add(gplusLoginButton);
        setSizeFull();
    }
}