package spring.board.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import spring.board.global.auth.PrincipalDetailService;
import spring.board.global.auth.PrincipalDetails;
import spring.board.global.filter.MyFilter;
import spring.board.global.jwt.JwtAuthenticationFilter;
import spring.board.global.jwt.JwtAuthorizationFilter;
import spring.board.global.jwt.JwtProvider;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
  private final CorsConfig corsConfig;
  private final PrincipalDetailService principalDetailService;
  private final JwtProvider jwtProvider;
  @Bean
  public BCryptPasswordEncoder passwordEncoder() { // Password Encoder
    // 빈으로 등록만 해주면, 알아서 이 패스워드 인코더를 찾아서 암호화후에 DB와 비교
    return new BCryptPasswordEncoder();
  }
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder sharedObject = http.getSharedObject(AuthenticationManagerBuilder.class);

    sharedObject.userDetailsService(this.principalDetailService);
    AuthenticationManager authenticationManager = sharedObject.build();
    http.authenticationManager(authenticationManager);

    // 스프링 시큐리티의 필터 앞에 커스텀 필터를 거는 방법
    http.addFilterBefore(new MyFilter(), BasicAuthenticationFilter.class);
    http.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    http
        .addFilter(corsConfig.corsFilter()) // CORS 필터를 추가해주기
        .csrf(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .addFilter(new JwtAuthenticationFilter(authenticationManager, jwtProvider)) // 내가 만든 필터를 등록하기
        .addFilter(new JwtAuthorizationFilter(authenticationManager))
        .authorizeHttpRequests(authorizeRequest ->
            authorizeRequest
                .requestMatchers(
                    AntPathRequestMatcher.antMatcher("/post/create"),
                    AntPathRequestMatcher.antMatcher("/post/delete/**"),
                    AntPathRequestMatcher.antMatcher("/post/update/**")
                ).authenticated()
                .requestMatchers(
                    AntPathRequestMatcher.antMatcher("/**")
                ).permitAll()
        )
        .headers(
            headersConfigurer ->
                headersConfigurer
                    .frameOptions(
                        HeadersConfigurer.FrameOptionsConfig::sameOrigin
                    )
        );

    return http.build();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    // 정적 리소스 spring security 대상에서 제외
    return (web) ->
        web
            .ignoring()
            .requestMatchers(
                PathRequest.toStaticResources().atCommonLocations()
            );
  }

}
