package org.vaadin.example.oauth;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * UI content when the user is not logged in yet.
 */
@Route("login")
@PageTitle("Login")
public class LoginScreen extends VerticalLayout {

    /**
     * URL that Spring uses to connect to Google services
     */
    private static final String URL = "/oauth2/authorization/google";

    public LoginScreen() {

        setPadding(true);
        setAlignItems(Alignment.CENTER);

        Anchor gplusLoginButton = new Anchor(URL, "Login with Google");
        gplusLoginButton.getStyle().set("margin-top", "100px");
        add(gplusLoginButton);
        setSizeFull();
    }
}