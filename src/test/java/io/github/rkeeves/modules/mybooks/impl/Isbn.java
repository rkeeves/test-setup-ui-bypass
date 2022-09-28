package io.github.rkeeves.modules.mybooks.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Isbn {

    @JsonProperty("isbn")
    private String isbn;
}
