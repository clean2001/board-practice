package spring.board.global.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);

  }

  // 인증, 권한이 필요한 요청이 들어오면 이 필터를 타게된다.
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    System.out.println("Authorization: 인증이나 권한이 필요한 주소 요청 됨");

    String jwtHeader = request.getHeader("Authorization");
    System.out.println("jwtHeader: " + jwtHeader);

    // JWT 토큰을 검증해서 정상적인 사용자인지 검증

    if(jwtHeader == null || !jwtHeader.startsWith("Bearer")) { // 정상적이지 않은 상황
      chain.doFilter(request, response);
      return;
    }

    // JWT 토큰을 검증해서 정상적인 사용자인지 확인
    String token = request.getHeader("Authorization").replace("Bearer", "");

    super.doFilterInternal(request, response, chain);
  }
}
