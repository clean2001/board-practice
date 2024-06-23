package spring.board.domain.member.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import spring.board.domain.member.domain.Member;
import spring.board.domain.member.dto.MemberDto;
import spring.board.domain.member.service.MemberService;

@RequiredArgsConstructor
@RestController
public class MemberController {
  private final MemberService memberService;

  @PostMapping("/register")
  public ResponseEntity<Long> registerMember(@Valid @RequestBody MemberDto memberDto) {
    Long memberId = memberService.registerMember(memberDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(memberId);
  }


  @GetMapping("/login")
  public String login() {
//    Long memberId = memberService.registerMember(memberDto);
    return "login";
  }
}
