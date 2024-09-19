package uz.online.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.online.blog.dto.PostDTO;
import uz.online.blog.entity.Post;
import uz.online.blog.mapper.PostMapper;
import uz.online.blog.repository.CommentRepository;
import uz.online.blog.repository.LikesRepository;
import uz.online.blog.repository.PostRepository;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final LikesService likesService;
    private final CommentService commentService;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper,  LikesService likesService, CommentService commentService) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.likesService = likesService;
        this.commentService = commentService;
    }

    @Override
    public Post save(PostDTO postDTO) {
        Post post = postMapper.fromDto(postDTO);
        return postRepository.save(post);
    }

    @Override
    public List<Post> findAll() {
        return List.of();
    }

    @Override
    public Post findById(Integer id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    @Override
    public void delete(Integer id) {
        postRepository.deleteById(id);
        likesService.deleteByPostId(id);
        commentService.deleteByPostId(id);
    }

    @Override
    public Post update(Integer id, PostDTO postDTO) {
        return null;
    }

    @Override
    public List<Post> finByTitle(String title) {
        return postRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public Page<Post> getPageBySize(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}
