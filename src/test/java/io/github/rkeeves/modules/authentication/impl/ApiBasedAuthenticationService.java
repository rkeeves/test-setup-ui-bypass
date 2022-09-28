package io.github.rkeeves.modules.authentication.impl;

import io.github.rkeeves.modules.authentication.AuthenticationService;
import io.github.rkeeves.modules.authentication.AuthenticatedHero;
import io.github.rkeeves.modules.facts.Hero;
import io.github.rkeeves.modules.facts.FactsService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.HttpStatus;

@RequiredArgsConstructor
public class ApiBasedAuthenticationService implements AuthenticationService {

    private final FactsService factsService;

    @Override
    public AuthenticatedHero authenticate(Hero hero) {
        RestAssured.baseURI = factsService.getApiBaseUri();
        final var loginRespone = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(LoginRequest.builder()
                        .username(hero.getUsername())
                        .password(hero.getPassword())
                        .build())
                .post("/Account/v1/Login")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().as(LoginResponse.class);
        return AuthenticatedHero.builder()
                .userId(loginRespone.getUserId())
                .username(loginRespone.getUsername())
                .token(loginRespone.getToken())
                .expires(loginRespone.getExpires())
                .build();
    }
}
