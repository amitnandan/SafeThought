package net.safethoughts.blog.controller;

import jakarta.validation.Valid;
import net.safethoughts.blog.payload.CommentDto;
import net.safethoughts.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class CommentController {


    private CommentService commentService ;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment( @PathVariable("postId") Long postId , @Valid @RequestBody CommentDto commentDto){

       return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }



    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentByPostId(@PathVariable("postId") Long postId ){

        return new ResponseEntity<>(commentService.getAllComments(postId), HttpStatus.CREATED);
    }


    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentByPostId(@PathVariable("postId") Long postId ,
                                                         @PathVariable("commentId") Long commentId
                                                         )
    {

        return new ResponseEntity<>(commentService.getCommentById(postId , commentId), HttpStatus.FOUND);
    }
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentByPostId(@PathVariable("postId") Long postId ,
                                                         @PathVariable("commentId") Long commentId,
                                                         @Valid @RequestBody CommentDto commentDto
    )
    {

        return new ResponseEntity<>(commentService.updateCommentById(postId , commentId , commentDto), HttpStatus.FOUND);
    }



    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable("postId") Long postId ,
                                                         @PathVariable("commentId") Long commentId
    )
    {
        commentService.deleteCommentById(postId , commentId);
        return new ResponseEntity<>("Comment Deleted Successfully", HttpStatus.OK);
    }



}
