package ar.edu.unq.cookitbackend.utils;

import ar.edu.unq.cookitbackend.dto.request.UserRequestDto;
import ar.edu.unq.cookitbackend.model.User;

public class Converter {

    public static User toUser(UserRequestDto request) {
        return User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .lastname(request.getLastname())
                .imageUrl(request.getImageUrl())
                .build();
    }
}
