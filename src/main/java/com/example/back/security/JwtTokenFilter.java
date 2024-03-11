package com.example.back.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain) throws ServletException, IOException {
        String jwt = getToken(request);
        if (jwt == null) {
            chain.doFilter(request, response);
            return;
        }

        Claims tokenBody = jwtTokenProvider.parseToken(jwt);

        if (tokenBody != null) {
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(buildAuthToken(tokenBody));
        }

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken buildAuthToken(Claims claims) {
        return new UsernamePasswordAuthenticationToken(claims.get("username"),
                "",
                List.of(new SimpleGrantedAuthority("USER")));  // List of roles
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }
        return header.substring("Bearer ".length());
    }
}
