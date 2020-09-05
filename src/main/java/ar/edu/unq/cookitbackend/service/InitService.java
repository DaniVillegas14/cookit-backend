package ar.edu.unq.cookitbackend.service;

import ar.edu.unq.cookitbackend.service.initial.InitialRecipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
@Transactional
public class InitService {

    @Autowired
    private InitialRecipe initialRecipe;

    @PostConstruct
    public void initialize() throws IOException {
        initialRecipe.createRecipes();
    }
}
