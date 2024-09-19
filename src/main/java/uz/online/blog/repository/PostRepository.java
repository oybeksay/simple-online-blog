package uz.online.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.online.blog.entity.Post;

import java.util.List;

@Transactional
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("select p from Post p where p.title ilike %:query% or p.body ilike %:query%")
    List<Post>  findByTitleAndBodyContainingIgnoreCase(@Param("query") String query);

}