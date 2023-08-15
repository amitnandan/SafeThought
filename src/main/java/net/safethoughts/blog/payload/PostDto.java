package net.safethoughts.blog.payload;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "PostDto Model Information"
)
public class PostDto {

    private Long id ;
    @Schema(
            description = "Blog Post Title"
    )

    @NotEmpty
    @Size(min = 2,message = "Title should have atleast 2 characters")
    private String title ;


    @Schema(
            description = "Blog Post Description"
    )

    @NotEmpty
    @Size(min = 10,message = "Atleast 10 characters should be there")
    private String description ;

    @Schema(
            description = "Blog Post Content"
    )
    @NotEmpty
    private String content;



    private Set<CommentDto> comments;

    @Schema(
            description = "Blog Post Category"
    )
    private Long categoryId;
}
