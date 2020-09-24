package ar.edu.unq.cookitbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor
public class JwtResponse {

    private long id;
    private String email;
    private String token;
}
