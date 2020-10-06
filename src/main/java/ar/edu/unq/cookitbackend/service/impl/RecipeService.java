package ar.edu.unq.cookitbackend.service.impl;

import ar.edu.unq.cookitbackend.dto.request.CommentRequestDto;
import ar.edu.unq.cookitbackend.dto.request.RecipeDto;
import ar.edu.unq.cookitbackend.dto.response.PageableRecipeResponseDto;
import ar.edu.unq.cookitbackend.dto.response.RecipeResponseDto;
import ar.edu.unq.cookitbackend.exception.NotFoundException;
import ar.edu.unq.cookitbackend.model.Comment;
import ar.edu.unq.cookitbackend.model.Recipe;
import ar.edu.unq.cookitbackend.model.User;
import ar.edu.unq.cookitbackend.persistence.RecipeRepository;
import ar.edu.unq.cookitbackend.persistence.UserRepository;
import ar.edu.unq.cookitbackend.service.IRecipes;
import ar.edu.unq.cookitbackend.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RecipeService implements IRecipes {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Page<PageableRecipeResponseDto> getAllRecipes(Optional<String> search,
                                                         Pageable pageable) {
        Page<Recipe> pageableRecipes = recipeRepository.findAllBy(search.orElse(""), pageable);
        return pageableRecipes.map(Converter::toPageableRecipeDto);
    }

    @Override
    public Recipe createRecipe(RecipeDto recipeDto) {
        Optional<User> user = userRepository.findById(recipeDto.getUserId());
        if (!user.isPresent()) {
            throw new RuntimeException("No se encuentra un usuario con ese id");
        }
        Recipe newRecipe = Converter.toRecipe(recipeDto);
        newRecipe.setUser(user.get());
        user.get().addRecipe(newRecipe);
        return recipeRepository.save(newRecipe);
    }

    @Override
    public RecipeResponseDto getRecipe(Long id) throws NotFoundException {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Receta no encontrada"));

        return Converter.toRecipeResponseDto(recipe);
    }

    @Override
    public void createComment(CommentRequestDto request) throws NotFoundException {
        Recipe recipe = recipeRepository.findById(request.getIdRecipe())
                .orElseThrow(() -> new NotFoundException("Receta no encontrada"));

        User user = userRepository.findById(request.getIdUser())
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        recipe.getComments().add(Converter.toComment(request.getMessage(), recipe, user));
        recipeRepository.save(recipe);
    }

}
