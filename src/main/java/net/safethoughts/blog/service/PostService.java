package net.safethoughts.blog.service;

import net.safethoughts.blog.entity.Post;
import net.safethoughts.blog.payload.PostDto;
import net.safethoughts.blog.payload.PostResponse;

import java.util.List;

public interface PostService  {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPost(int pageNo , int pageSize , String sortBy , String sortDir);

    PostDto getPostById ( Long id);


    PostDto updatePost( PostDto postDto , Long id);

    void deletePost(Long id);

    List<PostDto> getPostByCategory( Long categoryId);
}
