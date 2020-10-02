package ar.edu.unq.cookitbackend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class UserCommentResponseDto {
    private String name;
    private String imageUrl;
}
