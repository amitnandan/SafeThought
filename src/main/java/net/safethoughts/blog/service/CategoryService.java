package net.safethoughts.blog.service;

import net.safethoughts.blog.payload.CategoryDto;

import java.util.List;

public interface CategoryService {


    CategoryDto addCategory( CategoryDto categoryDto);


    CategoryDto getCategory( Long category_id );


    List<CategoryDto> getAllCategories();


    CategoryDto updateCategory( Long id , CategoryDto categoryDto);

    void  deleteCategory( Long categoryId);
}
