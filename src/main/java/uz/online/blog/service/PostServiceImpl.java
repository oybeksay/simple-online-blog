package uz.online.blog.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.online.blog.dto.PostDTO;
import uz.online.blog.entity.Likes;
import uz.online.blog.entity.Post;
import uz.online.blog.mapper.PostMapper;
import uz.online.blog.repository.LikesRepository;
import uz.online.blog.repository.PostRepository;

import java.util.List;

@Service
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final LikesRepository likesRepository;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper, LikesRepository likesRepository) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.likesRepository = likesRepository;
    }

    @Override
    public Post save(PostDTO postDTO) {
        Post post = postMapper.fromDto(postDTO);
        return postRepository.save(post);
    }

    @Override
    public Post findById(Integer id) {
       return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    @Override
    public void delete(Integer id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post update(Integer id, PostDTO postDTO) {
        postRepository.updateTitleAndBodyById(postDTO.getTitle(),postDTO.getBody(),id);
        return null;
    }

    @Override
    public List<Post> finByTitleOrDestcription(String query) {
        return postRepository.findByTitleAndBodyContainingIgnoreCase(query);
    }

    @Override
    public Page<Post> getPageBySize(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}
