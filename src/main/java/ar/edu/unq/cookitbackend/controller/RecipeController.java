package ar.edu.unq.cookitbackend.controller;

import ar.edu.unq.cookitbackend.dto.request.RecipeDto;
import ar.edu.unq.cookitbackend.dto.response.RecipeResponseDto;
import ar.edu.unq.cookitbackend.exception.NotFoundException;
import ar.edu.unq.cookitbackend.model.Recipe;
import ar.edu.unq.cookitbackend.service.IRecipes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private IRecipes recipeService;

    @GetMapping
    public ResponseEntity<Page<Recipe>> getAll(@RequestParam ("search") Optional<String> search,
                                               Pageable pageable) {
        return new ResponseEntity<>(recipeService.getAllRecipes(search, pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable ("id") Long id) {
        return new ResponseEntity<>(recipeService.getRecipe(id), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Recipe> addNewRecipe(@RequestBody RecipeDto recipeDto) {
        Recipe recipe = recipeService.createRecipe(recipeDto);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }
}
