package example.com.models;

import example.com.enums.Role;

public record AuthorizedSession(
        Long userId,
        String username,
        String password,
        String token,
        Role role
) {
}
