package net.safethoughts.blog.controller;

import net.safethoughts.blog.payload.CategoryDto;
import net.safethoughts.blog.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }


    //build add category REST API
    //@PreAuthorize("hasRole('ROLE_ADMIN')")


    @PreAuthorize( "hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDto> addCategory( @RequestBody CategoryDto categoryDto){
CategoryDto savedCategoryDto = categoryService.addCategory(categoryDto);
        return  new ResponseEntity<>(savedCategoryDto, HttpStatus.CREATED);
    }


    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategory( @PathVariable("id") Long categoryId){

            CategoryDto categoryDto = categoryService.getCategory(categoryId);
            return ResponseEntity.ok(categoryDto);
    }


    @GetMapping()
    public ResponseEntity<List<CategoryDto>> getCategory(){

        List<CategoryDto> categoriesDto= categoryService.getAllCategories();
        return ResponseEntity.ok(categoriesDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable("id") Long categoryId,
            @RequestBody CategoryDto categoryDto
    ){

        CategoryDto responseCategoryDto = categoryService.updateCategory(categoryId , categoryDto);
        return new ResponseEntity<>(responseCategoryDto,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId){

        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Deleted Succesfully");
    }

}
