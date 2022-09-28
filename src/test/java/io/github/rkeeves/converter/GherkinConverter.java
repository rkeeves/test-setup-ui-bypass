package io.github.rkeeves.converter;

import io.cucumber.java.ParameterType;
import io.github.rkeeves.modules.facts.Hero;
import io.github.rkeeves.modules.facts.FactsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

public class GherkinConverter {

    @Autowired
    private FactsService factsService;

    @ParameterType("Login Logan|MyBooks Maya")
    public Hero hero(String gherkinname){
        return factsService.findByGherkinname(gherkinname)
                .orElseThrow(() ->
                        new NoSuchElementException("Hero with gherkinname " + gherkinname + " was not found"));
    }
}
