package ar.edu.unq.cookitbackend.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRequestDto {

    private String email;
    private String name;
    private String lastname;
    private String imageUrl;
}
