package io.github.rkeeves.modules.facts.impl;

import io.github.rkeeves.modules.facts.FactsService;
import io.github.rkeeves.modules.facts.Hero;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
public class YmlBasedFactsService implements FactsService {

    private String apiBaseUri;

    private List<Hero> heroes;

    private Map<String, Hero> heroesCache;

    @PostConstruct
    void createCache() {
        this.heroesCache = heroes.stream()
                .collect(Collectors.toMap(Hero::getGherkinname, Function.identity()));
    }

    @Override
    public Optional<Hero> findByGherkinname(String gherkinname) {
        return Optional.ofNullable(heroesCache.get(gherkinname));
    }
}
