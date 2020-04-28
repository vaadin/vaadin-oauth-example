package org.vaadin.example;

import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.oauth.LoginScreen;
import org.vaadin.example.backend.UserSession;

@SpringComponent
public class ApplicationServiceInitListener
        implements VaadinServiceInitListener {
    @Autowired
    UserSession userSession;

    @Override
    public void serviceInit(ServiceInitEvent serviceInitEvent) {
        serviceInitEvent.getSource().addUIInitListener(uiInitEvent -> {
            uiInitEvent.getUI().addBeforeEnterListener(enterEvent -> {
                if (!userSession.isLoggedIn() && !LoginScreen.class
                        .equals(enterEvent.getNavigationTarget())) {
                    enterEvent.rerouteTo(LoginScreen.class);
                }
            });
        });
    }
}
