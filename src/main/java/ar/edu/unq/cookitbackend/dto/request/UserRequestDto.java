package ar.edu.unq.cookitbackend.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class UserRequestDto {

    private String email;
    private String name;
    private String lastname;
    private String imageUrl;
    private String password;
}
