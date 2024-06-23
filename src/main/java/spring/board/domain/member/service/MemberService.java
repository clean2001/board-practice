package spring.board.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.board.domain.member.domain.Member;
import spring.board.domain.member.dto.MemberDto;
import spring.board.domain.member.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class MemberService {
  private final MemberRepository memberRepository;
  private final BCryptPasswordEncoder passwordEncoder;
  public Long registerMember(MemberDto memberDto) {
    String encodedPassword = passwordEncoder.encode(memberDto.getPassword());
    Member savedMember = memberRepository.save(new Member(memberDto.getEmail(),
        memberDto.getNickname(),
        encodedPassword));

    return savedMember.getMemberId();
  }
}
