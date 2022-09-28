package io.github.rkeeves.modules.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticatedHero {

    private String username;

    private String userId;

    private String token;

    private String expires;
}
