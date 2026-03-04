package com.bruno.spring.Project.Spring.data.dto.security;

import java.util.Date;

public record TokenDTO(
        String userName,
        Boolean authenticated,
        Date created,
        Date expiration,
        String accessToken,
        String refreshToken

) {
}
