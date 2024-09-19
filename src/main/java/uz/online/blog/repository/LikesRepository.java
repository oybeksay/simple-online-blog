package uz.online.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import uz.online.blog.entity.Likes;

import java.util.Optional;

@Transactional
public interface LikesRepository extends JpaRepository<Likes, Integer> {
  Integer countByPostId(Integer postId);
  Optional<Likes> findByUserIdAndPostId(Integer userId, Integer postId);

  void deleteByUserId(Integer id);

    void deleteByPostId(Integer postId);
}