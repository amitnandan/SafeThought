package net.safethoughts.blog.service;

import net.safethoughts.blog.entity.Post;
import net.safethoughts.blog.payload.PostDto;

import java.util.List;

public interface PostService  {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPost();

    PostDto getPostById ( Long id);


    PostDto updatePost( PostDto postDto , Long id);

    void deletePost(Long id);
}
