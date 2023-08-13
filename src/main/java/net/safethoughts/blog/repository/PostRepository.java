package net.safethoughts.blog.repository;

import net.safethoughts.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {



    public List<Post> findByCategoryId(Long categoryId);


}
