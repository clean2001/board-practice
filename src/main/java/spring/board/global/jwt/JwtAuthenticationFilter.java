package spring.board.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import spring.board.domain.member.domain.Member;
import spring.board.global.auth.PrincipalDetailService;
import spring.board.global.auth.PrincipalDetails;

// 스프링 시큐리티에 있는 필터임 (UsernamePasswordAuthenticationFilter)
// /login 요청해서 post로 username, password 전송하면 이 필터가 동작을 함
//
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private final AuthenticationManager authenticationManager;

  // /login에 요청을 하면 로그인 실행을 위해 동작한다.
  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {
    System.out.println("JwtAuthenticationFilter line23");

    //== TODO ==//
    // 1. username, password 받아서
    // 2. 정상인지 로그인 시도 해보기. authenticationManager로 로그인 시도를 하면
    // PrincipalDetailsService가 호출, loadUserByUsername() 함수가 실행 (UserDetailsService 인터페이스에 내장돼있음)
    // 3. PrincipalDetails를 세션에 담고 => role 권한 관리를 해주기 위함. role 없으면 걍 안해도됨
    // 4. JWT 토큰을 만들어서 응답해주기

    ObjectMapper om = new ObjectMapper();
    Authentication authentication = null;
    try {
      Member member = om.readValue(request.getInputStream(), Member.class);

      System.out.println("line 39: member " + member);

      UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
          member.getEmail(), member.getPassword());

      // authentication엔 내가 로그인한 정보가 담긴다.
      authentication = authenticationManager.authenticate(token);

      PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

      System.out.println("line 52: usename:" + principalDetails.getUsername());

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    System.out.println("line 56 =================================================");

    // authentication 객체를 리턴하는 이유: 세션에 저장하면 시큐리티가 알아서 권한관리 해주기 때문이다.
    // JWT를 쓰면서 세션을 쓸 이유는 없지만, 권한 관리가 편하려고 리턴해주는거임
    return authentication;
  }

  // attemptAuthentication 메서드 다음에 실행됨 (인증이 정상적으로 되었을때)
  // 여기서 JWT 토큰을 만들어서, 리퀘스트 요청한 사용자의 응답으로 JWT 토큰을 넘겨주면 된다.
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult) throws IOException, ServletException {
    System.out.println("Authentication 완료!!!!");

  }
}
