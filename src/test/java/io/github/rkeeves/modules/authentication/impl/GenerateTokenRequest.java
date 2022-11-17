package io.github.rkeeves.modules.authentication.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenerateTokenRequest {

    @JsonProperty("userName")
    private String username;

    @JsonProperty("password")
    private String password;
}
