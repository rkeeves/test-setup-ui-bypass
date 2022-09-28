package io.github.rkeeves.modules.facts;

import java.util.Optional;

public interface FactsService {

    String getApiBaseUri();

    Optional<Hero> findByGherkinname(String gherkkinname);
}
