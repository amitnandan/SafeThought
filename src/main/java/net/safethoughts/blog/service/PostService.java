package net.safethoughts.blog.service;

import net.safethoughts.blog.payload.PostDto;

public interface PostService  {
    PostDto createPost(PostDto postDto);

}
