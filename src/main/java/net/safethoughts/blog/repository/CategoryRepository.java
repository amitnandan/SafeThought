package net.safethoughts.blog.repository;


import net.safethoughts.blog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category,Long> {


}
