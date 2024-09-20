package uz.online.blog.service;

import uz.online.blog.dto.LikesDTO;
import uz.online.blog.entity.Likes;

public interface LikesService {

    Likes addLike(LikesDTO likesDTO);

    Integer getLikesCountByPostId(Integer postId);

    void deleteLikesById(Integer postId, Integer userId);

}
