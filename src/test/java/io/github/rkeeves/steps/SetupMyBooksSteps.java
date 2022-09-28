package io.github.rkeeves.steps;

import io.cucumber.java.en.Given;
import io.github.rkeeves.modules.books.BookService;
import io.github.rkeeves.modules.browsersession.BrowserSessionService;
import io.github.rkeeves.modules.mybooks.MyBooksService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public class SetupMyBooksSteps {

    @Autowired
    private BrowserSessionService browserSessionService;

    @Autowired
    private BookService bookService;

    @Autowired
    private MyBooksService myBooksService;

    @Given("current account already has {int} books")
    public void accountForAliceWasSetupToHaveOwnedBooksBooks(int bookCount) {
        final var authenticatedHero = browserSessionService.getLoggedInHero()
                .orElseThrow(() ->
                        new IllegalStateException("Wasn't able to tell the logged in account from browser cookies"));
        final var allIsbns = bookService.findAllIsbns();
        final var wantedIsbns = allIsbns.stream()
                .limit(bookCount)
                .collect(Collectors.toList());
        myBooksService.resetMyCollection(authenticatedHero, wantedIsbns);
    }
}
