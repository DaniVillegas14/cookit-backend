package ar.edu.unq.cookitbackend.service;

import ar.edu.unq.cookitbackend.model.Ingredient;
import ar.edu.unq.cookitbackend.model.Step;
import ar.edu.unq.cookitbackend.model.User;
import ar.edu.unq.cookitbackend.service.initial.InitUser;
import ar.edu.unq.cookitbackend.service.initial.InitialIngredient;
import ar.edu.unq.cookitbackend.service.initial.InitialRecipe;
import ar.edu.unq.cookitbackend.service.initial.InitialStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class InitService {

    @Autowired
    private InitialRecipe initialRecipe;

    @Autowired
    private InitUser initUser;

    @PostConstruct
    public void initialize() throws IOException {
        User user = initUser.createUser();
        initialRecipe.createRecipes(user);
    }
}
