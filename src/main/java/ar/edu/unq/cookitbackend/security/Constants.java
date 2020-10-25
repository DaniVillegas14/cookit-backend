package ar.edu.unq.cookitbackend.security;

public class Constants {

    public static final String[] AUTH_WHITELIST = {
            "/auth/login/social",
            "/auth/register",
            "/test",
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };
}
