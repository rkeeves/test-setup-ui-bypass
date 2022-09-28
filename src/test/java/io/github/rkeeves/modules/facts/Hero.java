package io.github.rkeeves.modules.facts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hero {

    private String gherkinname;

    private String username;

    private String password;
}
