package spring.board.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.board.domain.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
