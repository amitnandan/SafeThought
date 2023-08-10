package net.safethoughts.blog.service.impl;

import net.safethoughts.blog.entity.Comment;
import net.safethoughts.blog.entity.Post;
import net.safethoughts.blog.exceptions.BlogAPIException;
import net.safethoughts.blog.exceptions.ResourceNotFoundException;
import net.safethoughts.blog.payload.CommentDto;
import net.safethoughts.blog.repository.CommentRepository;
import net.safethoughts.blog.repository.PostRepository;
import net.safethoughts.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {


    private CommentRepository commentRepository;


    private ModelMapper modelMapper ;


    private PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository , ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);

        //retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException(
                        "POST","ID",postId
                ));


        //set post to comment entity
        comment.setPost(post);

        //save comment to db

        Comment savedComment = commentRepository.save(comment);
        return  mapToDTO(comment);


    }

    @Override
    public List<CommentDto> getAllComments(Long postId) {

        List<Comment> commentList = commentRepository.findByPostId(postId);

        return commentList.stream().map( comment -> mapToDTO(comment))
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId ,Long commentId) {

        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("COMMENT","ID",commentId)
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new ResourceNotFoundException("COMMENT","ID",commentId)
        );

        //check comment belongs to particular post or not
        if( !(comment.getPost().getId()).equals(post.getId()))
            throw  new BlogAPIException(HttpStatus.BAD_REQUEST

            ,"Comment does not belong to post");


        CommentDto commentDto = mapToDTO(comment);
        return commentDto;

    }

    @Override
    public CommentDto updateCommentById(Long postId, Long commentId, CommentDto commentDto) {

        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("COMMENT","ID",commentId)
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new ResourceNotFoundException("COMMENT","ID",commentId)
        );

        if( !(comment.getPost().getId()).equals(post.getId()))
            throw  new BlogAPIException(HttpStatus.BAD_REQUEST

                    ,"Comment does not belong to post");


        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment savedComment = commentRepository.save(comment);

        return mapToDTO(savedComment);

    }

    @Override
    public void deleteCommentById(Long postId, Long commentId) {

        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("COMMENT","ID",commentId)
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new ResourceNotFoundException("COMMENT","ID",commentId)
        );

        if( !(comment.getPost().getId()).equals(post.getId()))
            throw  new BlogAPIException(HttpStatus.BAD_REQUEST

                    ,"Comment does not belong to post");


        commentRepository.deleteById(commentId);

    }


    private CommentDto mapToDTO( Comment comment)
    {

        CommentDto commentDto = modelMapper.map(comment,CommentDto.class);
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    private Comment mapToEntity( CommentDto commentDto)
    {
        Comment comment = modelMapper.map(commentDto,Comment.class);
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
        return comment;
    }
}
