package io.github.rkeeves.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.rkeeves.modules.facts.Hero;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePageSteps {

    @When("Profile page gets visited")
    public void profilePageGetsVisited() {
        open("/profile");
    }

    @Then("Profile page shows details for {hero}")
    public void profilePageShowsDetailsForUser(Hero hero) {
        $("#userName-value").shouldHave(text(hero.getUsername()));
    }
}
