package spring.board.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.board.domain.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
