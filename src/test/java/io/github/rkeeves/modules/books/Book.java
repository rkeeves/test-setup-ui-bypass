package io.github.rkeeves.modules.books;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("title")
    private String title;

    @JsonProperty("subTitle")
    private String subTitle;

    @JsonProperty("author")
    private String author;

    @JsonProperty("publish_date")
    private String publishDate;

    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("pages")
    private Integer pages;

    @JsonProperty("description")
    private String description;

    @JsonProperty("website")
    private String website;
}
