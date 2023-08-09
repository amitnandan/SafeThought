package net.safethoughts.blog.controller;

import net.safethoughts.blog.entity.Post;
import net.safethoughts.blog.payload.PostDto;
import net.safethoughts.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private  PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto)
    {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<PostDto>> getAllPost(){

        List<PostDto> postDtoList = postService.getAllPost();
        return ResponseEntity.ok(postDtoList);
    }

    @GetMapping("{post-id}")
    public ResponseEntity<PostDto> getPostById( @PathVariable("post-id") Long id){
        return  new ResponseEntity<>(postService.getPostById(id),HttpStatus.FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePost( @RequestBody PostDto postDto ,
        @PathVariable("id") Long id)
    {
        PostDto postDto1 = postService.updatePost(postDto,id);
        return new ResponseEntity<>(postDto1,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePost( @PathVariable("id")
                                      Long id
                                      )
    {
        postService.deletePost(id);
        return ResponseEntity.ok("Deleted Successfully ");
    }

}
