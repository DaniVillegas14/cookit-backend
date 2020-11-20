package ar.edu.unq.cookitbackend.service.impl;

import ar.edu.unq.cookitbackend.dto.request.EditUserRequestDto;
import ar.edu.unq.cookitbackend.dto.request.UserRequestDto;
import ar.edu.unq.cookitbackend.dto.response.UserResponseDto;
import ar.edu.unq.cookitbackend.exception.CreateDocumentationException;
import ar.edu.unq.cookitbackend.exception.NotFoundException;
import ar.edu.unq.cookitbackend.exception.PasswordIncorrectException;
import ar.edu.unq.cookitbackend.model.Recipe;
import ar.edu.unq.cookitbackend.model.User;
import ar.edu.unq.cookitbackend.persistence.RecipeRepository;
import ar.edu.unq.cookitbackend.persistence.UserRepository;
import ar.edu.unq.cookitbackend.service.IDocumentationService;
import ar.edu.unq.cookitbackend.service.IUserService;
import ar.edu.unq.cookitbackend.utils.Converter;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private IDocumentationService documentationService;

    @Override
    public UserResponseDto getUserByToken(String token) {
        String jwtToken = token.substring(7);

        String[] split_string = jwtToken.split("\\.");
        String base64EncodedBody = split_string[1];
        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        String email = body.split("\"")[3];

        User user = userRepository.findByEmail(email);

        List<Recipe> availableRecipes = recipeRepository.findRecipesByIdUser(user.getId());

        UserResponseDto userResponseDto = Converter.toUserResponseDto(user, availableRecipes);

        List<Recipe> availableFavorites = recipeRepository.findRecipesFavoritesByIdUser(user.getId());

        userResponseDto.setFavorites(Converter.toRecipesResponseDto(availableFavorites));

        return userResponseDto;
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
    public void removeRecipeToFavorites(Long userId, Long favoriteRecipeId) {
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
        user.removeRecipeToFavorite(recipe);
        recipe.removeFavoriteOf(user);
        userRepository.save(user);
    }

    @Override
    public void followUser(Long userId, Long userFollowId) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent()) {
            throw new NotFoundException("No se encuentra el usuario id");
        }
        User user = optionalUser.get();
        Optional<User> optionalFollowerUser = userRepository.findById(userFollowId);
        if(!optionalFollowerUser.isPresent()) {
            throw new NotFoundException("No se encuentra el usuario id");
        }
        User followerUser = optionalFollowerUser.get();
        user.addFollow(followerUser);
        userRepository.save(user);
    }

    @Override
    public void unfollowUser(Long userId, Long userFollowId) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent()) {
            throw new NotFoundException("No se encuentra el usuario id");
        }
        User user = optionalUser.get();
        Optional<User> optionalFollowerUser = userRepository.findById(userFollowId);
        if(!optionalFollowerUser.isPresent()) {
            throw new NotFoundException("No se encuentra el usuario id");
        }
        User followerUser = optionalFollowerUser.get();
        user.removeFollow(followerUser);
        userRepository.save(user);
    }

    @Override
    public void editUser(EditUserRequestDto request) throws NotFoundException, PasswordIncorrectException, IOException, CreateDocumentationException {
        User user = userRepository.findByEmail(request.getEmail());

        if(user == null) {
            throw new NotFoundException("No se encontro el usuario con dicho email");
        }

        if (user.getIsGoogleAccount()) {
            user.setName(request.getName());
            user.setLastname(request.getLastname());
            setImageFromUser(user, request.getImageUrl());
            userRepository.save(user);
        } else {
            setUserData(user, request);
        }
    }

    private void setImageFromUser(User user, String imageUrl) throws IOException, CreateDocumentationException {
        if (imageUrl != null) {
            user.setImageUrl(documentationService.createImageDocumentation(imageUrl));
        }
    }

    private void setUserData(User user, EditUserRequestDto request) throws PasswordIncorrectException, IOException, CreateDocumentationException {
        if (isPasswordCorrect(request.getCurrentPassword(), user.getPassword())) {
            user.setName(request.getName());
            user.setLastname(request.getLastname());
            setImageFromUser(user, request.getImageUrl());
            setNewPassword(user, request.getNewPassword());
            userRepository.save(user);
        } else {
            throw new PasswordIncorrectException();
        }
    }

    private void setNewPassword(User user, String newPassword) {
        if (newPassword != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
        }
    }

    private Boolean isPasswordCorrect(String passwordToVerify, String passwordCorrectly){
        return passwordEncoder.matches(passwordToVerify, passwordCorrectly);
    }
}
