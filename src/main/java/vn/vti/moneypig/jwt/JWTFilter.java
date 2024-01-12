//package vn.vti.moneypig.jwt;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.security.Key;
//import java.util.Base64;
//
//public class JWTFilter extends OncePerRequestFilter {
//    private static final String AUTHORIZATION_HEADER = "Authorization";
//    private static final String TOKEN_PREFIX = "Bearer ";
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String token = extractTokenFromRequest(request);
//
//        if (token == null || token.isEmpty()) {
//            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Missing or invalid token");
//            return;
//        }
//
//        try {
//            Claims claims = JWTUtility.getInstance().parseToken(token);
//            // You can perform additional validation or processing with the claims here
//            // Add the claims to the request attributes to make them accessible to other components
//            request.setAttribute("claims", claims);
//        } catch (Exception e) {
//            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid token");
//            return;
//        }
//
//        filterChain.doFilter(request, response);
//    }
//    private String extractTokenFromRequest(HttpServletRequest request) {
//        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
//        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
//            return bearerToken.substring(TOKEN_PREFIX.length());
//        }
//        return null;
//    }
//}
