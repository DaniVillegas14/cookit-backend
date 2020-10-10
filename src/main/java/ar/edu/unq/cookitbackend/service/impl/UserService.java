package ar.edu.unq.cookitbackend.service.impl;

import ar.edu.unq.cookitbackend.dto.response.UserResponseDto;
import ar.edu.unq.cookitbackend.model.Recipe;
import ar.edu.unq.cookitbackend.model.User;
import ar.edu.unq.cookitbackend.persistence.RecipeRepository;
import ar.edu.unq.cookitbackend.persistence.UserRepository;
import ar.edu.unq.cookitbackend.service.IUserService;
import ar.edu.unq.cookitbackend.utils.Converter;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public UserResponseDto getUserByToken(String token) {
        String jwtToken = token.substring(7);

        String[] split_string = jwtToken.split("\\.");
        String base64EncodedBody = split_string[1];
        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        String email = body.split("\"")[3];

        User user = userRepository.findByEmail(email);

        return Converter.toUserResponseDto(user);
    }

    @Override
    public void addRecipeToFavorites(Long userId, Long favoriteRecipeId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent()) {
            throw new RuntimeException("No se encuentra el usuario id");
        }
        User user = optionalUser.get();
        Optional<Recipe> optionalRecipe = recipeRepository.findById(favoriteRecipeId);
        if(!optionalRecipe.isPresent()) {
            throw new RuntimeException("No se encuentra la receta");
        }
        Recipe recipe = optionalRecipe.get();
        user.addFavoriteRecipes(recipe);
        recipe.addFavoriteOf(user);
        userRepository.save(user);
    }

    @Override
    public void removeRecipeToFavorites(Long userId, Long favoriteId) {

    }
}
