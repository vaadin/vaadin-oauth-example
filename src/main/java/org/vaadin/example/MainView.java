
package org.vaadin.example;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.backend.UserSession;

import javax.annotation.PostConstruct;

@Route("")
public class MainView extends VerticalLayout {

    @Autowired
    UserSession userSession;

    @PostConstruct
    public void init() {
        userSession.getUser().getFirstName();

        Div div = new Div();
        div.setText("Hello " + userSession.getUser().getFirstName() + " "
                + userSession.getUser().getLastName());
        div.getElement().getStyle().set("margin-left", "42%");
        div.getElement().getStyle().set("font-size", "xx-large");

        Image image = new Image(userSession.getUser().getPicture(),
                "User Image");
        image.getElement().getStyle().set("margin-left", "45%");

        Anchor logout = new Anchor("/logout", "Logout");
        logout.getElement().getStyle().set("margin-left", "46%");

        logout.getElement().setAttribute("router-ignore", true);
        add(div, image, logout);
    }
}
