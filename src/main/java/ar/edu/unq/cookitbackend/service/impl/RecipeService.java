package ar.edu.unq.cookitbackend.service.impl;

import ar.edu.unq.cookitbackend.model.Recipe;
import ar.edu.unq.cookitbackend.persistence.RecipeRepository;
import ar.edu.unq.cookitbackend.service.IRecipes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RecipeService implements IRecipes {

    @Autowired
    RecipeRepository recipeRepository;

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }
}
