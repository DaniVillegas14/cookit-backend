package ar.edu.unq.cookitbackend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class UserCommentResponseDto {
    private Long idUser;
    private String name;
    private String lastname;
    private String imageUrl;
}
