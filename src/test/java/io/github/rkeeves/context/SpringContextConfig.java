package io.github.rkeeves.context;

import io.github.rkeeves.modules.authentication.AuthenticationService;
import io.github.rkeeves.modules.authentication.impl.ApiBasedAuthenticationService;
import io.github.rkeeves.modules.books.BookService;
import io.github.rkeeves.modules.books.impl.ApiBasedBookService;
import io.github.rkeeves.modules.browsersession.BrowserSessionService;
import io.github.rkeeves.modules.browsersession.impl.SelenideBasedBrowserSessionService;
import io.github.rkeeves.modules.facts.FactsService;
import io.github.rkeeves.modules.facts.impl.YmlBasedFactsService;
import io.github.rkeeves.modules.mybooks.MyBooksService;
import io.github.rkeeves.modules.mybooks.impl.ApiBasedMyBooksService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class SpringContextConfig {

    @Bean
    @ConfigurationProperties(prefix = "facts")
    public FactsService heroService() {
        return new YmlBasedFactsService();
    }

    @Bean
    public BrowserSessionService browserSessionService() {
        return new SelenideBasedBrowserSessionService();
    }

    @Bean
    public AuthenticationService authenticationService(FactsService factsService) {
        return new ApiBasedAuthenticationService(factsService);
    }

    @Bean
    public BookService bookService(FactsService factsService) {
        return new ApiBasedBookService(factsService);
    }

    @Bean
    public MyBooksService myBooksService(FactsService factsService) {
        return new ApiBasedMyBooksService(factsService);
    }
}