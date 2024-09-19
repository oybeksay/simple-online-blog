package uz.online.blog.service;

import uz.online.blog.dto.CommentDTO;
import uz.online.blog.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment addComment(CommentDTO commentDTO);

    List<Comment> getCommentsByPostId(Integer postId);

    Comment getCommentById(Integer commentId);

    void deleteCommentByPostIdAndUserId(Integer commentId);

    void deleteByPostId(Integer id);
}
