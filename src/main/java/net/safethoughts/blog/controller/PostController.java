package net.safethoughts.blog.controller;

import jakarta.validation.Valid;
import net.safethoughts.blog.entity.Post;
import net.safethoughts.blog.payload.PostDto;
import net.safethoughts.blog.payload.PostResponse;
import net.safethoughts.blog.service.PostService;
import net.safethoughts.blog.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto)
    {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

//    @GetMapping()
//    public ResponseEntity<List<PostDto>> getAllPost(){
//
//        List<PostDto> postDtoList = postService.getAllPost();
//        return ResponseEntity.ok(postDtoList);
//    }

    //adding pagination to the getAll Post


        @GetMapping()
    public ResponseEntity<PostResponse> getAllPost( @RequestParam(value = "pageNo" , defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize" , defaultValue = AppConstants.DEFAULT_PAGE_SIZE , required = false) int pageSize,
            @RequestParam(value ="sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY , required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir ){


        PostResponse postResponseDtoList = postService.getAllPost(pageNo , pageSize , sortBy , sortDir);
        return ResponseEntity.ok(postResponseDtoList);
    }


    @GetMapping("/{post-id}")
    public ResponseEntity<PostDto> getPostById( @PathVariable("post-id") Long id){
        return  new ResponseEntity<>(postService.getPostById(id),HttpStatus.FOUND);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost( @Valid @RequestBody PostDto postDto ,
        @PathVariable("id") Long id)
    {
        PostDto postDto1 = postService.updatePost(postDto,id);
        return new ResponseEntity<>(postDto1,HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost( @PathVariable("id")
                                      Long id
                                      )
    {
        postService.deletePost(id);
        return ResponseEntity.ok("Deleted Successfully ");
    }

}
