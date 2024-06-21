package spring.board.domain.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.board.domain.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
  @Modifying(clearAutomatically = true)
  @Query("UPDATE Post p SET p.title = :title, contents = :contents WHERE p.postId = :postId")
  void updatePost(@Param("postId") Long postId, @Param("title") String title, @Param("contents") String contents);
  @Modifying(clearAutomatically = true)
  @Query("UPDATE Post p SET p.delYn = :delYn WHERE p.postId = :postId")
  void updateDelYn(@Param("postId") Long postId, @Param("delYn") boolean delYn);

  Page<Post> findByDelYn(boolean delYn, Pageable pageable);
}
