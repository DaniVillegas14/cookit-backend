package ar.edu.unq.cookitbackend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @Builder
public class CommentResponseDto {
    private String message;
    private LocalDateTime created_at;
    private UserCommentResponseDto owner;
}
