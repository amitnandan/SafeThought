package net.safethoughts.blog.payload;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Long id ;

    @NotEmpty
    @Size(min = 2,message = "Title should have atleast 2 characters")
    private String title ;


    @NotEmpty
    @Size(min = 10,message = "Atleast 10 characters should be there")
    private String description ;

    @NotEmpty
    private String content;


    private Set<CommentDto> comments;
}
