package uz.online.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.online.blog.dto.PostDTO;
import uz.online.blog.entity.Post;

import java.util.List;

public interface PostService {
    Post save(PostDTO postDTO);

    Post findById(Integer id);

    void delete(Integer id);

    Post update(Integer id, PostDTO postDTO);

    List<Post> finByTitleOrDestcription(String query);

    Page<Post> getPageBySize(Pageable pageable);
}
