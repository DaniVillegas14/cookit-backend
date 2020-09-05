package ar.edu.unq.cookitbackend.controller;

import ar.edu.unq.cookitbackend.model.Recipe;
import ar.edu.unq.cookitbackend.service.IRecipes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private IRecipes iRecipes;

    @GetMapping
    public ResponseEntity<List<Recipe>> getAll() {
        return new ResponseEntity<>(iRecipes.getAllRecipes(), HttpStatus.OK);
    }
}
