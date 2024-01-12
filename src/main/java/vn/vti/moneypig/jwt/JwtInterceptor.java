package vn.vti.moneypig.jwt;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.Date;

public class JwtInterceptor implements HandlerInterceptor {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    @Override
    public boolean preHandle(HttpServletRequest  request, HttpServletResponse response, Object handler) throws Exception {
        String token = extractTokenFromRequest(request);
        if (token == null || token.isEmpty() || isValidToken(token)) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Missing or invalid token");
            return false;
        }
        try {
            Claims claims = JWTUtility.getInstance().parseToken(token);
            // You can perform additional validation or processing with the claims here
            // Add the claims to the request attributes to make them accessible to other components
            request.setAttribute("claims", claims);
        } catch (Exception e) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid token");
            return false;
        }

        return true;
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

        public  boolean isValidToken(String token) {
        try {
           Claims claims =  JWTUtility.getInstance().parseToken(token);
            Date expirationDate = claims.getExpiration();
            Date currentDate = new Date();
            // Check if the token has expired
            return expirationDate == null || !expirationDate.before(currentDate); // Token has expired
            // Token is valid and not expired
        } catch (Exception e) {
            return false; // Token is invalid
        }
    }
}
