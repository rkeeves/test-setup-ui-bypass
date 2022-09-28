package io.github.rkeeves.modules.mybooks.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddBooksToAccountRequest {

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("collectionOfIsbns")
    private List<Isbn> collectionOfIsbns;
}
