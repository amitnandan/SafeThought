package net.safethoughts.blog.service.impl;

import net.safethoughts.blog.entity.Category;
import net.safethoughts.blog.entity.Post;
import net.safethoughts.blog.exceptions.ResourceNotFoundException;
import net.safethoughts.blog.payload.CategoryDto;
import net.safethoughts.blog.payload.PostDto;
import net.safethoughts.blog.payload.PostResponse;
import net.safethoughts.blog.repository.CategoryRepository;
import net.safethoughts.blog.repository.PostRepository;
import net.safethoughts.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {


    private PostRepository postRepository;

    private ModelMapper modelMapper;

    private CategoryRepository categoryRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository , ModelMapper modelMapper , CategoryRepository categoryRepository)
    {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

@Override
    public PostDto createPost(PostDto postDto ) {


    Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(
            ()->  new ResourceNotFoundException("Category","Id" , postDto.getCategoryId())
    );
    Post post = mapToEntity(postDto);

    post.setCategory(category);

    Post newPost = postRepository.save(post);

    PostDto postResponse = mapToDto(newPost);
    return postResponse;
    }

    @Override
    public PostResponse getAllPost( int pageNo , int pageSize , String sortBy , String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();

        //Pageable pageable = PageRequest.of(pageNo,pageSize);
        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);
        Page<Post> postsPageObject = postRepository.findAll(pageable);

        //get content from page object

        List<Post> listOfPosts = postsPageObject.getContent();

        List<PostDto> postDtoList =
                listOfPosts.stream()
                .map(post -> mapToDto(post))
                .collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtoList);
        postResponse.setPageNo(postsPageObject.getNumber());
        postResponse.setPageSize(postsPageObject.getSize());
        postResponse.setTotalElements(postsPageObject.getTotalElements());
        postResponse.setTotalPages(postsPageObject.getTotalPages());
        postResponse.setLast(postsPageObject.isLast());
        return postResponse;


    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException(
                        "POST","ID",id
                ));

        PostDto postDto = mapToDto(post);
       return postDto;

    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException(
                        "POST","ID",id
                ));

        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(
                ()->  new ResourceNotFoundException("Category","Id" , postDto.getCategoryId())
        );

        post.setCategory(category);
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

    @Override
    public List<PostDto> getPostByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                ()->  new ResourceNotFoundException("Category","Id" , categoryId)
        );

        List<Post> posts = postRepository.findByCategoryId(categoryId);


        return posts.stream().map( post -> modelMapper.map(post,PostDto.class))
                .collect(Collectors.toList());
    }


    private PostDto mapToDto(Post post){

        PostDto postDto = modelMapper.map(post,PostDto.class);
//        PostDto postDto = new PostDto();
//
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
            return postDto;
    }



    private Post mapToEntity(PostDto postDto){

        Post post = modelMapper.map(postDto,Post.class);
//        Post post = new Post();
//        //   post.setId(postDto.getId());
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;
    }
}
