package ar.edu.unq.cookitbackend.controller;

import ar.edu.unq.cookitbackend.dto.request.RecipeDto;
import ar.edu.unq.cookitbackend.dto.response.RecipeResponseDto;
import ar.edu.unq.cookitbackend.exception.NotFoundException;
import ar.edu.unq.cookitbackend.model.Recipe;
import ar.edu.unq.cookitbackend.service.IRecipes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private IRecipes recipeService;

    @GetMapping
    public ResponseEntity<List<Recipe>> getAll() {
        return new ResponseEntity<>(recipeService.getAllRecipes(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Recipe> addNewRecipe(@RequestBody RecipeDto recipeDto) {
        Recipe recipe = recipeService.createRecipe(recipeDto);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<RecipeResponseDto>> search(@RequestParam ("query") String query) throws NotFoundException {
        List<RecipeResponseDto> response = recipeService.getRecipesByQuery(query);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
