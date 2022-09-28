package io.github.rkeeves.steps;

import io.cucumber.java.en.And;
import io.github.rkeeves.modules.authentication.AuthenticationService;
import io.github.rkeeves.modules.browsersession.BrowserSessionService;
import io.github.rkeeves.modules.facts.Hero;
import org.springframework.beans.factory.annotation.Autowired;

public class SetupBrowserSessionSteps {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private BrowserSessionService browserSessionService;

    @And("browser is already logged in as {hero}")
    public void browserIsLoggedInAs(Hero hero) {
        final var authenticatedHero =  authenticationService.authenticate(hero);
        browserSessionService.loginHero(authenticatedHero);
    }
}
