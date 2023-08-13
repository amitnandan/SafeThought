package net.safethoughts.blog.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String name ;

    private String description;

    @OneToMany( mappedBy = "category",
                cascade = CascadeType.ALL,
                orphanRemoval  = true // if there is not reference then it will be removed
    )
    private List<Post> posts ;

}
