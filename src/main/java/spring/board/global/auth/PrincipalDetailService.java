package spring.board.global.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.board.domain.member.domain.Member;
import spring.board.domain.member.repository.MemberRepository;
import spring.board.domain.member.service.MemberService;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {
  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    Member member = memberRepository.findByEmail(name);
    return new PrincipalDetails(member);
  }
}
