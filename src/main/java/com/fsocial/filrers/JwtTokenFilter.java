package com.fsocial.filrers;

import com.fsocial.models.User;
import com.fsocial.utils.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    @Value("${api.prefix}")
    private String apiPrefix;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isByPassToken(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized");
            return;
        }
        String token = authHeader.substring(7);
        String id = jwtTokenUtil.getEmailFromToken(token);
        if(id == null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User userDetails = (User) userDetailsService.loadUserByUsername(id);
            if(jwtTokenUtil.validateToken(token,userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
    private boolean isByPassToken(@NotNull HttpServletRequest request) {
        final List<Pair<String,String>> bypassTokens = Arrays.asList(
                Pair.of("/"+apiPrefix+"/users/login", "POST"),
                Pair.of("/"+apiPrefix+"/majors", "GET"),
                Pair.of("/"+apiPrefix+"/users/register", "POST")
        );

        String requestPath = request.getServletPath();
        String requestMethod = request.getMethod();
        if(requestPath.startsWith("/v3/api-docs")
                && requestMethod.equals("GET") || requestPath.startsWith("/swagger-ui")
                && requestMethod.equals("GET") ||
                requestPath.startsWith("/swagger-ui.html")
                && requestMethod.equals("GET")) {
            return true;
        }
        if(requestPath.startsWith("/"+apiPrefix+"/users/validator-email")
                && requestMethod.equals("POST")) {
            return true;
        }
        for (Pair<String,String> bypassToken : bypassTokens) {
            if (bypassToken.getLeft().contains(requestPath) && bypassToken.getRight().contains(requestMethod)) {
                return true;
            }
        }
        return false;
    }
}
