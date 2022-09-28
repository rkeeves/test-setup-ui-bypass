package io.github.rkeeves.modules.browsersession.impl;

import com.codeborne.selenide.Selenide;
import io.github.rkeeves.modules.authentication.AuthenticatedHero;
import io.github.rkeeves.modules.browsersession.BrowserSessionService;
import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.Cookie;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SelenideBasedBrowserSessionService implements BrowserSessionService {

    private static final String COOKIE_NAME_USERNAME = "userName";

    private static final String COOKIE_NAME_USERID = "userID";

    private static final String COOKIE_NAME_TOKEN = "token";

    private static final String COOKIE_NAME_EXPIRES = "expires";

    @Override
    public void clearLoggedInHero() {
        Selenide.open("");
        Selenide.webdriver().driver().clearCookies();
    }

    @Override
    public void loginHero(AuthenticatedHero authenticatedHero) {
        Selenide.open("");
        Selenide.webdriver().driver().clearCookies();
        final var options = Selenide.webdriver().driver().getAndCheckWebDriver().manage();
        options.addCookie(new Cookie(COOKIE_NAME_USERNAME, authenticatedHero.getUsername()));
        options.addCookie(new Cookie(COOKIE_NAME_USERID, authenticatedHero.getUserId()));
        options.addCookie(new Cookie(COOKIE_NAME_TOKEN, authenticatedHero.getToken()));
        options.addCookie(new Cookie(COOKIE_NAME_EXPIRES, authenticatedHero.getExpires()));
    }

    @Override
    public Optional<AuthenticatedHero> getLoggedInHero() {
        final var options = Selenide.webdriver().driver().getAndCheckWebDriver().manage();
        final var cookies = Stream.of(
                        COOKIE_NAME_USERNAME,
                        COOKIE_NAME_USERID,
                        COOKIE_NAME_TOKEN,
                        COOKIE_NAME_EXPIRES)
                .map(cookieName -> {
                    final var cookie = options.getCookieNamed(cookieName);
                    return Pair.of(cookieName, cookie == null ? null : cookie.getValue());
                })
                .filter(pair -> pair.getValue() != null)
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
        if (cookies.size() < 4) {
            return Optional.empty();
        }
        final var authenticatedHero = AuthenticatedHero.builder()
                .username(cookies.get(COOKIE_NAME_USERNAME))
                .userId(cookies.get(COOKIE_NAME_USERID))
                .token(cookies.get(COOKIE_NAME_TOKEN))
                .expires(cookies.get(COOKIE_NAME_EXPIRES))
                .build();
        return Optional.of(authenticatedHero);
    }
}
