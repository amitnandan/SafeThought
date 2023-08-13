package net.safethoughts.blog.service.impl;

import net.safethoughts.blog.entity.Category;
import net.safethoughts.blog.exceptions.ResourceNotFoundException;
import net.safethoughts.blog.payload.CategoryDto;
import net.safethoughts.blog.repository.CategoryRepository;
import net.safethoughts.blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {

        Category category = modelMapper.map(categoryDto,Category.class);
        Category savedCategory = categoryRepository.save(category);

        return  modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Long category_id) {

        Category category = categoryRepository.findById(category_id).orElseThrow(
                ()-> new ResourceNotFoundException("Category","ID",category_id)
        );
        return  modelMapper.map(category,CategoryDto.class);

    }

    @Override
    public List<CategoryDto> getAllCategories() {

        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map((category) -> modelMapper.map(category,CategoryDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {

        Category category = categoryRepository.findById(id).orElseThrow(
                () -> {
                    throw new ResourceNotFoundException("Category", "ID", id);
                });
        //category.setId(categoryDto.getId());
        category.setDescription(categoryDto.getDescription());
        category.setName(categoryDto.getName());

        Category savedCategory = categoryRepository.save(category);

        return modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> {
                    throw new ResourceNotFoundException("Category", "ID", categoryId);
                });

        categoryRepository.deleteById(categoryId);


    }
}
