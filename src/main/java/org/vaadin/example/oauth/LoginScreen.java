package org.vaadin.example.oauth;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gson.Gson;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.RequestHandler;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinResponse;
import com.vaadin.flow.server.VaadinServletResponse;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.vaadin.example.backend.User;
import org.vaadin.example.backend.UserSession;

/**
 * UI content when the user is not logged in yet.
 */
@Route("")
@PageTitle("Login")
public class LoginScreen extends FlexLayout implements RequestHandler {

    private OAuth20Service service;

    @Value("${jpa-invoicer.gpluskey}")
    private String gpluskey;

    @Value("${jpa-invoicer.gplussecret}")
    private String gplussecret;

    @Autowired
    UserSession userSession;

    String redirectUrl;

    public LoginScreen() {

        VaadinSession.getCurrent().addRequestHandler(this);

        UI.getCurrent().getPage().executeJs("return window.location.href;")
                .then(jsonValue -> {
                    redirectUrl = jsonValue.asString();
                    service = createService(jsonValue.asString());
                    String url = service.createAuthorizationUrlBuilder()
                            .build();
                    Anchor gplusLoginButton = new Anchor(url,
                            "Login with Google");
                    add(gplusLoginButton);
                });

        setSizeFull();
    }

    @Override
    public boolean handleRequest(VaadinSession session, VaadinRequest request,
            VaadinResponse vaadinResponse) {
        if (request.getParameter("code") != null) {
            System.out.println("handleRequest!");
            String code = request.getParameter("code");

            try {
                OAuth2AccessToken accessToken = service.getAccessToken(code);
                OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET,
                        "https://www.googleapis.com/oauth2/v2/userinfo");
                service.signRequest(accessToken, oAuthRequest);
                Response oAuthResponse = service.execute(oAuthRequest);

                User user = new Gson().fromJson(oAuthResponse.getBody(),
                        User.class);
                userSession.setUser(user);
                this.getUI().get().access(() -> VaadinSession.getCurrent()
                        .removeRequestHandler(this));

                ((VaadinServletResponse) vaadinResponse)
                        .getHttpServletResponse()
                        .sendRedirect(redirectUrl + "main");
                return true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    private OAuth20Service createService(String url) {
        ServiceBuilder sb = new ServiceBuilder(gpluskey);
        sb.apiSecret(gplussecret);
        sb.defaultScope("email");
        String callBackUrl = url;
        if (callBackUrl.contains("#")) {
            callBackUrl = callBackUrl.substring(0, callBackUrl.indexOf("#"));
        }
        sb.callback(callBackUrl);
        return sb.build(GoogleApi20.instance());
    }
}