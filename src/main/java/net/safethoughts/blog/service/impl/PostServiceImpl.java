package net.safethoughts.blog.service.impl;

import net.safethoughts.blog.entity.Post;
import net.safethoughts.blog.payload.PostDto;
import net.safethoughts.blog.repository.PostRepository;
import net.safethoughts.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {


    private PostRepository postRepository;
    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
@Override
    public PostDto createPost(PostDto postDto) {

    Post post = new Post();
    post.setId(postDto.getId());
    post.setTitle(postDto.getTitle());
    post.setDescription(postDto.getDescription());
    post.setContent(postDto.getContent());

    Post newPost = postRepository.save(post);

    PostDto postResponse = new PostDto();
    postResponse.setId(newPost.getId());
    postResponse.setTitle(newPost.getTitle());
    postResponse.setDescription(newPost.getDescription());
    postResponse.setContent(newPost.getContent());
    return postResponse;
    }
}
