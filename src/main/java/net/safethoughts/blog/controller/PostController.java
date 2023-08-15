package net.safethoughts.blog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "CRUD REST APIs for Post Resource"
)
public class PostController {

    private  PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(
            summary = "Create Post REST API",
            description = "Create Post REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )

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


    @Operation(
            summary = "Get All Posts REST API",
            description = "Get All Posts REST API is used to fetch all the posts from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
        @GetMapping()
    public ResponseEntity<PostResponse> getAllPost( @RequestParam(value = "pageNo" , defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize" , defaultValue = AppConstants.DEFAULT_PAGE_SIZE , required = false) int pageSize,
            @RequestParam(value ="sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY , required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir ){


        PostResponse postResponseDtoList = postService.getAllPost(pageNo , pageSize , sortBy , sortDir);
        return ResponseEntity.ok(postResponseDtoList);
    }


    @Operation(
            summary = "Get Post By Id REST API",
            description = "Get Post By Id REST API is used to get single post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )

    @GetMapping("/{post-id}")
    public ResponseEntity<PostDto> getPostById( @PathVariable("post-id") Long id){
        return  new ResponseEntity<>(postService.getPostById(id),HttpStatus.FOUND);
    }

    @Operation(
            summary = "update Post REST API",
            description = "Update Post REST API is used to update a particular post in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost( @Valid @RequestBody PostDto postDto ,
        @PathVariable("id") Long id)
    {
        PostDto postDto1 = postService.updatePost(postDto,id);
        return new ResponseEntity<>(postDto1,HttpStatus.ACCEPTED);
    }



    @Operation(
            summary = "Delete Post REST API",
            description = "Delete Post REST API is used to delete a particular post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost( @PathVariable("id")
                                      Long id
                                      )
    {
        postService.deletePost(id);
        return ResponseEntity.ok("Deleted Successfully ");
    }

    @GetMapping("/category/{category-id}")
    public ResponseEntity<List<PostDto>> getPostByCateogry(@PathVariable("category-id") Long categoryId){

        List<PostDto> posts = postService.getPostByCategory(categoryId);
        return  new ResponseEntity<>(posts,HttpStatus.OK);
    }

}
