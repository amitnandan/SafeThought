package net.safethoughts.blog.service.impl;

import net.safethoughts.blog.entity.Post;
import net.safethoughts.blog.exceptions.ResourceNotFoundException;
import net.safethoughts.blog.payload.PostDto;
import net.safethoughts.blog.repository.PostRepository;
import net.safethoughts.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {


    private PostRepository postRepository;
    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
@Override
    public PostDto createPost(PostDto postDto) {

    Post post = mapToEntity(postDto);

    Post newPost = postRepository.save(post);

    PostDto postResponse = mapToDto(newPost);
    return postResponse;
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> listpost = postRepository.findAll();
        List<PostDto> postDtoList =
                listpost.stream()
                .map(post -> mapToDto(post))
                .collect(Collectors.toList());
        return postDtoList;


    }

    @Override
    public PostDto getPostById(Long id) {
       Optional<Post> post =  postRepository.findById(id);
       if( !post.isPresent())
           throw new ResourceNotFoundException("Post","Id",id);

       PostDto postDto = mapToDto(post.get());
       return postDto;

    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException(
                        "POST","ID",id
                ));


        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatedPost =  postRepository.save(post);

        PostDto updatedPostDto = mapToDto(updatedPost);
        return updatedPostDto;
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException(
                        "POST","ID",id
                ));


        postRepository.deleteById(id);
    }


    private PostDto mapToDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
            return postDto;
    }



    private Post mapToEntity(PostDto postDto){
        Post post = new Post();
        //   post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
}
