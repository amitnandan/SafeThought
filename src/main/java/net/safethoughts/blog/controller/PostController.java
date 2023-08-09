package net.safethoughts.blog.controller;

import net.safethoughts.blog.payload.PostDto;
import net.safethoughts.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    private  PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto)
    {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED)
    }
}