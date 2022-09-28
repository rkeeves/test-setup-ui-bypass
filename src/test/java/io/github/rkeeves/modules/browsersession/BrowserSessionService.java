package io.github.rkeeves.modules.browsersession;

import io.github.rkeeves.modules.authentication.AuthenticatedHero;

import java.util.Optional;

public interface BrowserSessionService {

    void clearLoggedInHero();

    void loginHero(AuthenticatedHero authenticatedHero);

    Optional<AuthenticatedHero> getLoggedInHero();
}
