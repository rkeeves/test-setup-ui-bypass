package io.github.rkeeves.modules.books.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.rkeeves.modules.books.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllBooksResponse {

    @JsonProperty("books")
    private List<Book> books;
}
