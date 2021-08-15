package com.renmoney_ha.configurations.security;

public class SecurityAuthConstants {
    public static final long TOKEN_EXPIRATION_TIME = 864_000_000;
    public static final String[] PUBLIC_URIS = new String[]{
            "/v1/login",
            "/**",
            // -- Swagger UI v3 (OpenAPI) Start
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/h2-console",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui/login/",
            "/swagger-ui/v1/login",
            "/swagger-ui/v1/register",
            "/swagger-ui/#/**",
            // -- Swagger UI v3 (OpenAPI) End
//            "/v1/login",

            "/v1/register"
    };
}
