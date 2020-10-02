package ar.edu.unq.cookitbackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
@Table(name="usuario")
public class User extends BaseEntity {

    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "lastname", nullable = false)
    private String lastname;
    @Column(name = "imageurl")
    private String imageUrl;
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<Recipe> recipes;
}
