package io.github.rkeeves.modules.mybooks.impl;

import io.github.rkeeves.modules.authentication.AuthenticatedHero;
import io.github.rkeeves.modules.facts.FactsService;
import io.github.rkeeves.modules.mybooks.MyBooksService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ApiBasedMyBooksService implements MyBooksService {

    private final FactsService factsService;

    @Override
    public void resetMyCollection(AuthenticatedHero authenticatedHero, List<String> isbnList) {
        deleteAllBooksOf(authenticatedHero);
        if (!isbnList.isEmpty()) {
            addBooksToAccount(authenticatedHero, isbnList);
        }
    }

    private void deleteAllBooksOf(AuthenticatedHero authenticatedHero) {
        RestAssured.baseURI = factsService.getApiBaseUri();
        RestAssured.given()
                .header("Authorization", "Bearer " + authenticatedHero.getToken())
                .queryParam("UserId", authenticatedHero.getUserId())
                .delete("/Bookstore/v1/Books")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    private void addBooksToAccount(AuthenticatedHero authenticatedHero, List<String> isbnList) {
        RestAssured.baseURI = factsService.getApiBaseUri();
        RestAssured.given()
                .header("Authorization", "Bearer " + authenticatedHero.getToken())
                .queryParam("UserId", authenticatedHero.getUserId())
                .contentType(ContentType.JSON)
                .body(AddBooksToAccountRequest.builder()
                        .userId(authenticatedHero.getUserId())
                        .collectionOfIsbns(asIsbnObjects(isbnList))
                        .build())
                .post("/Bookstore/v1/Books")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED);
    }

    private static List<Isbn> asIsbnObjects(List<String> isbnList) {
        return isbnList.stream()
                .map(Isbn::new)
                .collect(Collectors.toList());
    }
}
