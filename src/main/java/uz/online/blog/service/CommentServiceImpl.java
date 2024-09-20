package uz.online.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.online.blog.dto.CommentDTO;
import uz.online.blog.entity.Comment;
import uz.online.blog.entity.Post;
import uz.online.blog.mapper.CommentMapper;
import uz.online.blog.repository.CommentRepository;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PostService postService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper, PostService postService) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.postService = postService;
    }

    @Override
    public Comment addComment(CommentDTO commentDTO) {
        Post post = postService.findById(commentDTO.getPostId());
        Comment comment = commentMapper.fromDto(commentDTO);
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByPostId(Integer postId) {
        return commentRepository.findByPostId(postId);
    }

    @Override
    public Comment getCommentById(Integer commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    @Override
    public void deleteCommentByPostIdAndUserId(Integer postId, Integer userId) {
        Post post = postService.findById(postId);
        commentRepository.deleteByPostAndUserId(post,userId);
    }
}
