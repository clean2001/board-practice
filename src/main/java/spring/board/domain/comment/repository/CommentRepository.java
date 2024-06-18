package spring.board.domain.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.board.domain.comment.domain.Comment;
import spring.board.domain.member.domain.Member;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
