package spring.board.global.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import spring.board.domain.member.domain.Member;

@PropertySource("classpath:application-auth.properties")
@Service
public class JwtProvider {
  public static final String ACCESS_PREFIX_STRING = "Bearer ";
  public static final String ACCESS_HEADER_STRING = "Authorization";
  public static final String REFRESH_HEADER_STRING = "RefreshToken";

  private final String secretKey;
  private final long accessTokenExpiration;
  private final long refreshTokenExpiration;
  private final String issuer;
  private SecretKey signingKey;
  private String keyBase64Encoded; // properties에 정의된 값

  public JwtProvider(
      @Value("${secret-key}") String secretKey,
      @Value("${access-token-expiration}") String accessTokenExpiration,
      @Value("${refresh-token-expiration}") String refreshTokenExpiration,
      @Value("${issuer}") String issuer
  ) {
    this.secretKey = secretKey;
    this.accessTokenExpiration = Long.parseLong(accessTokenExpiration);
    this.refreshTokenExpiration = Long.parseLong(refreshTokenExpiration);
    this.issuer = issuer;
    this.keyBase64Encoded = Base64.getEncoder().encodeToString(secretKey.getBytes());
    this.signingKey = Keys.hmacShaKeyFor(keyBase64Encoded.getBytes());
  }


//  public String createToken(Long memberId, String email) {
//    Map<String, Object> claims = new HashMap<>();
//    claims.put("memberId", memberId);
//    claims.put("email", email);
//    String accesToken = Jwts.builder()
//        .subject(Long.toString(memberId))
//        .claims(claims)
//        .expiration()
//
//  }

  private String createAccessToken(Long memberId, String email) {
    Map<String, Object> claims = new HashMap<>();

    claims.put("memberId", memberId);
    claims.put("email", email);
    Date expiration = new Date(System.currentTimeMillis() + accessTokenExpiration);

    String accessToken = Jwts.builder()
        .subject(Long.toString(memberId))
        .claims(claims)
        .expiration(expiration)
        .signWith(this.signingKey)
        .compact();

    return accessToken;
  }


  private String createRefreshToken(Long memberId, String email) {
    Date expiration = new Date(System.currentTimeMillis() + refreshTokenExpiration);
    String refreshToken = Jwts.builder()
        .subject(Long.toString(memberId))
        .claim("memberId", memberId)
        .expiration(expiration)
        .signWith(this.signingKey)
        .compact();

    return refreshToken;
  }

}
