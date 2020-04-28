
package org.vaadin.example;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.backend.UserSession;

import javax.annotation.PostConstruct;

@Route("main")
public class MainView extends VerticalLayout {

    @Autowired
    UserSession userSession;

    @PostConstruct
    public void init() {
        userSession.getUser().getEmail();

        Div div = new Div();
        div.setText("Hello " + userSession.getUser().getEmail());
        div.getElement().getStyle().set("margin-left", "40%");
        div.getElement().getStyle().set("font-size", "xx-large");

        Image image = new Image(userSession.getUser().getPicture(),
                "UserImage");
        image.getElement().getStyle().set("margin-left", "40%");
        add(div, image);
    }
}
