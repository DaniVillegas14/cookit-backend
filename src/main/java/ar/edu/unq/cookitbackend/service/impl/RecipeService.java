package ar.edu.unq.cookitbackend.service.impl;

import ar.edu.unq.cookitbackend.dto.request.CommentRequestDto;
import ar.edu.unq.cookitbackend.dto.request.RecipeDto;
import ar.edu.unq.cookitbackend.dto.response.CommentResponseDto;
import ar.edu.unq.cookitbackend.dto.response.PageableCommentResponseDto;
import ar.edu.unq.cookitbackend.dto.response.PageableRecipeResponseDto;
import ar.edu.unq.cookitbackend.dto.response.RecipeResponseDto;
import ar.edu.unq.cookitbackend.exception.CreateDocumentationException;
import ar.edu.unq.cookitbackend.exception.NotFoundException;
import ar.edu.unq.cookitbackend.model.Comment;
import ar.edu.unq.cookitbackend.model.Recipe;
import ar.edu.unq.cookitbackend.model.User;
import ar.edu.unq.cookitbackend.persistence.CommentRepository;
import ar.edu.unq.cookitbackend.persistence.RecipeRepository;
import ar.edu.unq.cookitbackend.persistence.UserRepository;
import ar.edu.unq.cookitbackend.service.IDocumentationService;
import ar.edu.unq.cookitbackend.service.IRecipes;
import ar.edu.unq.cookitbackend.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecipeService implements IRecipes {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    IDocumentationService documentationService;

    @Override
    public Page<PageableRecipeResponseDto> getAllRecipes(Optional<String> search,
                                                         Pageable pageable) {
        Page<Recipe> pageableRecipes = recipeRepository.findAllBy(search.orElse(""), pageable);
        return pageableRecipes.map(Converter::toPageableRecipeDto);
    }

    @Override
    public Page<PageableRecipeResponseDto> findFollowersRecipes(Long userId, Pageable pageable) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new NotFoundException("No se encontro al usuario");
        }
        Page<Recipe> pageableRecipes = recipeRepository.findFollowersRecipes(userId, pageable);
        return pageableRecipes.map(Converter::toPageableRecipeDto);
    }

    @Override
    public RecipeDto createRecipe(RecipeDto recipeDto) throws IOException, CreateDocumentationException {
        Optional<User> user = userRepository.findById(recipeDto.getUserId());
        if (!user.isPresent()) {
            throw new RuntimeException("No se encuentra un usuario con ese id");
        }
        Recipe newRecipe = Converter.toRecipe(recipeDto);
        newRecipe.setUser(user.get());
        newRecipe.setAvailable(true);
        newRecipe.setImageUrl(documentationService.createImageDocumentation(recipeDto.getImage_url()));
        user.get().addRecipe(newRecipe);
        recipeRepository.save(newRecipe);
        return recipeDto;
    }

    @Override
    public RecipeResponseDto getRecipe(Long id) throws NotFoundException {
        Recipe recipe = recipeRepository.findByIdRecipe(id)
                .orElseThrow(() -> new NotFoundException("Receta no encontrada"));

        RecipeResponseDto response = Converter.toRecipeResponseDto(recipe);
        if (!recipe.getComments().isEmpty()) {
            Comment lastComment = commentRepository.findLastCommentByIdRecipe(id).get(0);
            response.setLastComment(Converter.toCommentResponseDto(lastComment));
        }

        return response;
    }

    @Override
    public CommentResponseDto createComment(CommentRequestDto request) throws NotFoundException {
        Recipe recipe = recipeRepository.findById(request.getIdRecipe())
                .orElseThrow(() -> new NotFoundException("Receta no encontrada"));

        User user = userRepository.findById(request.getIdUser())
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        Comment comment = Converter.toComment(request.getMessage(), recipe, user);
        recipe.addComment(comment);
        recipeRepository.save(recipe);

        return Converter.toCommentResponseDto(comment);
    }

    @Override
    public Page<CommentResponseDto> getCommentsByIdRecipe(Long id, Pageable pageable) throws NotFoundException {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Receta no encontrada"));

        Page<Comment> pageableComments = commentRepository.findAllBy(recipe.getId(), pageable);

        return pageableComments.map(Converter::toCommentResponseDto);
    }

    @Override
    public void deleteRecipeById(Long id) throws NotFoundException {
        Recipe recipe = recipeRepository.findByIdRecipe(id)
                .orElseThrow(() -> new NotFoundException("Receta no encontrada"));

        recipe.setAvailable(false);
        recipeRepository.save(recipe);
    }

}
