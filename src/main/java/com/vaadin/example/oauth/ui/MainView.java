
package com.vaadin.example.oauth.ui;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.example.oauth.data.UserSession;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * Application main class that is hidden to user before authentication.
 */
@Route("")
public class MainView extends VerticalLayout {

    @Autowired
    UserSession userSession;

    /**
     * After user has authenticated with Google login and has granted access to the
     * application on the Authorize application page, will see the information.
     */
    @PostConstruct
    public void init() {

        Div div = new Div();
        div.setText("Hello " + userSession.getUser().getFirstName() + " " + userSession.getUser().getLastName());
        div.getElement().getStyle().set("font-size", "xx-large");

        Image image = new Image(userSession.getUser().getPicture(), "User Image");

        // Spring maps the 'logout' url so we should ignore it
        Anchor logout = new Anchor("/logout", "Logout");
        logout.getElement().setAttribute("router-ignore", true);

        setAlignItems(Alignment.CENTER);
        add(div, image, logout);
    }
}
