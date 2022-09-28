package io.github.rkeeves.modules.books.impl;

import io.github.rkeeves.modules.books.Book;
import io.github.rkeeves.modules.books.BookService;
import io.github.rkeeves.modules.facts.FactsService;
import io.restassured.RestAssured;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ApiBasedBookService implements BookService {

    private final FactsService factsService;

    @Override
    public List<String> findAllIsbns() {
        RestAssured.baseURI = factsService.getApiBaseUri();
        return RestAssured.given()
                .get("/BookStore/v1/Books")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(GetAllBooksResponse.class)
                .getBooks()
                .stream()
                .map(Book::getIsbn)
                .collect(Collectors.toList());
    }
}
