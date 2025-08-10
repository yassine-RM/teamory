package org.teamory.backend.Filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.teamory.backend.Services.Jwt.KeycloakUserProvisioningService;

import java.io.IOException;

@Component
public class JwtUserProvisioningFilter extends OncePerRequestFilter {

    private final KeycloakUserProvisioningService provisioningService;

    public JwtUserProvisioningFilter(KeycloakUserProvisioningService provisioningService) {
        this.provisioningService = provisioningService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            provisioningService.provisionUser(jwt);
        }

        filterChain.doFilter(request, response);
    }
}

