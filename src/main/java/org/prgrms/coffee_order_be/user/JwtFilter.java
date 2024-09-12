package org.prgrms.coffee_order_be.user;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, java.io.IOException {

        String token = resolveToken(request);
        String method = request.getMethod();
        String requestURI = request.getRequestURI();

        if (token != null && jwtProvider.validateToken(token)) {
            Claims claims = jwtProvider.parseClaims(token);
            String role = claims.get("role", String.class);

            if (isUserUrl(requestURI, method) && "user".equals(role)) {
                filterChain.doFilter(request, response);
            } else if (isAdminUrl(requestURI, method) && "admin".equals(role)) {
                filterChain.doFilter(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                return;
            }
        } else {
            if (isUserUrl(requestURI, method) || isAdminUrl(requestURI, method)) response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
            else filterChain.doFilter(request, response);
        }
    }

    private String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            return token;
        }
        return null;
    }

    private boolean isUserUrl(String requestURI, String method) {
        return (requestURI.equals("/api/v1/orders") && "POST".equalsIgnoreCase(method)) ||
                (requestURI.matches("^/api/v1/orders/\\d+$") && "PUT".equalsIgnoreCase(method)) ||
                (requestURI.matches("^/api/v1/orders/\\d+$") && "DELETE".equalsIgnoreCase(method)) ||
                (requestURI.equals("/orders") && "GET".equalsIgnoreCase(method)) ||
                (requestURI.startsWith("/api/v1/coupon/issue")) ||
                (requestURI.startsWith("/api/v1/coupon/use")) ||
                (requestURI.startsWith("/api/v1/coupon/cancel"))
                ;
    }

    private boolean isAdminUrl(String requestURI, String method) {
        return (requestURI.startsWith("/admin")) ||
                (requestURI.equals("/api/v1/products") && "POST".equalsIgnoreCase(method)) ||
                (requestURI.matches("^/api/v1/products/\\d+$") && "PUT".equalsIgnoreCase(method)) ||
                (requestURI.matches("^/api/v1/products/\\d+$") && "DELETE".equalsIgnoreCase(method)) ||
                (requestURI.equals("/api/v1/coupon") && "DELETE".equalsIgnoreCase(method)) ||
                (requestURI.matches("^/api/v1/coupon/\\d+$") && "DELETE".equalsIgnoreCase(method))
                ;
    }
}
