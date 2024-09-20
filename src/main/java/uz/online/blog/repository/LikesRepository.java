package uz.online.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.online.blog.entity.Likes;
import uz.online.blog.entity.Post;

import java.util.List;
import java.util.Optional;

@Transactional
public interface LikesRepository extends JpaRepository<Likes, Integer> {
  Integer countByPostId(Integer postId);
  Optional<Likes> findByUserIdAndPostId(Integer userId, Integer postId);

  void deleteByUserId(Integer id);

  void deleteByPostId(Integer postId);

  @Query("from Likes l where l.post.id = ?1")
  List<Likes> findByPostId(Integer id);

  void deleteByPostAndUserId(Post post, Integer userId);
}