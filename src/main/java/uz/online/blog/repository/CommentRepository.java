package uz.online.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import uz.online.blog.entity.Comment;
import uz.online.blog.entity.Post;

import java.util.List;

@Transactional
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByPostId(Integer postId);

    void deleteByPostId(Integer postId);

    long deleteByPostAndUserId(Post post, Integer userId);
}