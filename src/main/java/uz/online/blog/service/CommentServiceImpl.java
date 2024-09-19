package uz.online.blog.service;

import org.springframework.stereotype.Service;
import uz.online.blog.dto.CommentDTO;
import uz.online.blog.entity.Comment;
import uz.online.blog.mapper.CommentMapper;
import uz.online.blog.repository.CommentRepository;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public Comment addComment(CommentDTO comment) {
        Comment newComment = commentMapper.fromDto(comment);
        return commentRepository.save(newComment);
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
    public void deleteCommentByPostIdAndUserId(Integer commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public void deleteByPostId(Integer id) {
        commentRepository.deleteByPostId(id);
    }
}
