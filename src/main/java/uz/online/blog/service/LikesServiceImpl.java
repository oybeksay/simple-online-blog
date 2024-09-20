package uz.online.blog.service;

import org.springframework.stereotype.Service;
import uz.online.blog.dto.LikesDTO;
import uz.online.blog.entity.Likes;
import uz.online.blog.entity.Post;
import uz.online.blog.mapper.LikesMapper;
import uz.online.blog.repository.LikesRepository;

@Service
public class LikesServiceImpl implements LikesService {
    private final LikesRepository likesRepository;
    private final LikesMapper likesMapper;
    private final PostService postService;

    public LikesServiceImpl(LikesRepository likesRepository, LikesMapper likesMapper, PostService postService) {
        this.likesRepository = likesRepository;
        this.likesMapper = likesMapper;
        this.postService = postService;
    }

    @Override
    public Likes addLike(LikesDTO likesDTO) {
        Post post = postService.findById(likesDTO.getPostId());
        Likes like = likesMapper.fromDto(likesDTO);
        like.setPost(post);
        if (likesRepository.findByUserIdAndPostId(likesDTO.getUserId(), likesDTO.getPostId()).isPresent()) {
            throw new RuntimeException("Member already liked");
        }
        return likesRepository.save(like);
    }

    @Override
    public Integer getLikesCountByPostId(Integer postId) {
        return likesRepository.countByPostId(postId);
    }

    @Override
    public void deleteLikesById(Integer postId, Integer userId) {
        Post post = postService.findById(postId);
        likesRepository.deleteByPostAndUserId(post, userId);
    }

}
