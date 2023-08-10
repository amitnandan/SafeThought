package net.safethoughts.blog.service;
import net.safethoughts.blog.entity.Comment;
import net.safethoughts.blog.payload.CommentDto;
import java.util.List;

public interface CommentService {

    CommentDto createComment( Long postId , CommentDto commentDto);

    List<CommentDto> getAllComments(Long postId );

    CommentDto getCommentById( Long postId , Long commentId);

    CommentDto updateCommentById( Long postId , Long commentId , CommentDto commentDto);

    void  deleteCommentById( Long postId , Long commentId);

}
