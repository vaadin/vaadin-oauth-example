
package com.vaadin.example.oauth.ui;

import com.vaadin.example.oauth.data.UserSession;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinServletRequest;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.annotation.security.PermitAll;

/**
 * Application main class that is hidden to user before authentication.
 *
 * The class is annotated with {@code @PermitAll} to allow only authenticated
 * users to view the class.
 */
@Route("")
@PermitAll
public class MainView extends VerticalLayout {

    private static final String LOGOUT_SUCCESS_URL = "/";

    public MainView(UserSession userSession) {
        Div div = new Div();
        div.setText("Hello " + userSession.getUser().getFirstName() + " " + userSession.getUser().getLastName());
        div.getElement().getStyle().set("font-size", "xx-large");

        Image image = new Image(userSession.getUser().getPicture(), "User Image");

        Button logoutButton = new Button("Logout", click -> {
            UI.getCurrent().getPage().setLocation(LOGOUT_SUCCESS_URL);
            SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
            logoutHandler.logout(
                    VaadinServletRequest.getCurrent().getHttpServletRequest(), null,
                    null);
        });

        setAlignItems(Alignment.CENTER);
        add(div, image, logoutButton);
    }
}
