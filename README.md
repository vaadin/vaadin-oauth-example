# Example app for using the Google OAuth service with Vaadin and Spring Boot

This is an example project demonstrating how to configure and use the OAuth services provided by Google, i.e. how to enable 'Login with Google' functionality in Vaadin apps.

** NOTE **
To run this example, you will need to apply for an API key from Google and add it to the `application.properties` file under `src/main/resources`. Please follow [this tutorial](https://vaadin.com/learn/tutorials/google-login) for instructions on how to do that, and more information about how this example works. 

For local testing, you can add `http://localhost:8080` to the `URIs` section and `http://localhost:8080/login/oauth2/code/google` to the `Authorized URIs` section in the Google API dashboard once you have created a key. You will still need an Authorized URL that is not localhost for the API Key to work; these are just for convenience.

## Running the Application
Import the project to the IDE of your choosing as a Maven project.

Run the application using `mvn spring-boot:run` or by running the `Application` class directly from your IDE.

Open http://localhost:8080/ in your browser.

If you want to run the application locally in the production mode, run `mvn spring-boot:run -Pproduction`.

## More Information

- [Vaadin + OAuth tutorial](https://vaadin.com/learn/tutorials/google-login)
- [Vaadin documentation](https://vaadin.com/docs)
- [Using Vaadin and Spring](https://vaadin.com/docs/v14/flow/spring/tutorial-spring-basic.html) article

