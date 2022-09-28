package io.github.rkeeves.modules.mybooks;

import io.github.rkeeves.modules.authentication.AuthenticatedHero;

import java.util.List;

public interface MyBooksService {

    void resetMyCollection(AuthenticatedHero authenticatedHero, List<String> isbnList);
}
