package ar.edu.unq.cookitbackend.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryRequestDto {
    private String name;
}
