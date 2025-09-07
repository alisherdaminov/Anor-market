package Anor.market.infrastucture.config.validation;

import Anor.market.application.dto.JWT.JwtDTO;
import Anor.market.shared.enums.Roles;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

public class JwtTokens {

    private static final int tokenLiveTime = 1000 * 3600 * 72;
    private static final String secretKey = "U6zBr2R9sFpAq7mLdEvKjNxPwYtGhZmXcVbJnQoTsWaSdFgHjKlMzXyCrVuBpLkJ";
    private static final SecureRandom secureRandom = new SecureRandom(); // thread - safe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder().withoutPadding();

    public static String encode(String email, Integer userId, List<Roles> roles) {
        String rolesString = roles.stream().map(Roles::name).collect(Collectors.joining(","));
        Map<String, String> extraClaims = new HashMap<>();
        extraClaims.put("roles", rolesString);
        extraClaims.put("userId", String.valueOf(userId));
        extraClaims.put("randomData", UUID.randomUUID().toString().repeat(8));

        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + tokenLiveTime))
                .signWith(getSignInKey())
                .compact();
    }


    public static JwtDTO decode(String email) {
        Claims claims = Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(email)
                .getPayload();
        String claimsEmail = claims.getSubject();
        Integer id = Integer.valueOf((String) claims.get("userId"));
        String strRole = (String) claims.get("roles");
        Roles rolesEnum = Roles.valueOf(strRole);
        return new JwtDTO(id, claimsEmail, rolesEnum);

    }

    public static String generateRefreshToken() {
        byte[] randomBytes = new byte[64];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    private static SecretKey getSignInKey() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
