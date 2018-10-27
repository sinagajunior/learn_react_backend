package sinaga.junior.projecttimelapse.security;


import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import sinaga.junior.projecttimelapse.domain.User;

import javax.jws.soap.SOAPBinding;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static sinaga.junior.projecttimelapse.security.SecurityConstants.EXPIRATION_TIME;
import static sinaga.junior.projecttimelapse.security.SecurityConstants.SECRET;

@Component
public class JwtTokenProvider {


    public String generateToken(Authentication authentication) {
        User user =(User) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime()+EXPIRATION_TIME);
        String userId = Long.toString(user.getId());

        Map<String,Object> claims = new HashMap<>();
        claims.put("id",Long.toString(user.getId()));
        claims.put("username",user.getUsername());
        claims.put("fullname",user.getFullname());

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
    }


    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return  true;
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return  false;
    }

// get user id from token
public Long getUsertIdFromJWT (String token) {
        Claims claim = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String id =(String) claim.get("id");
        return  Long.parseLong(id);

}


}
