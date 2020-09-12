package ar.edu.unq.cookitbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="ingredients")
public class Ingredient extends BaseEntity {
    @Column(name = "quantity_weight", nullable = false)
    private String quantity_weight;
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "receta_id")
    @JsonIgnore
    private Recipe recipe;
}
