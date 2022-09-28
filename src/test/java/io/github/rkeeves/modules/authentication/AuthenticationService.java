package io.github.rkeeves.modules.authentication;

import io.github.rkeeves.modules.facts.Hero;

public interface AuthenticationService {

    AuthenticatedHero authenticate(Hero hero);
}
