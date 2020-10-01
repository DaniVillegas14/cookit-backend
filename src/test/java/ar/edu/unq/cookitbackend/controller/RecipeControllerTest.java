package ar.edu.unq.cookitbackend.controller;

import ar.edu.unq.cookitbackend.controller.RecipeController;
import ar.edu.unq.cookitbackend.model.Recipe;
import ar.edu.unq.cookitbackend.security.JwtAuthenticationEntryPoint;
import ar.edu.unq.cookitbackend.security.JwtRequestFilter;
import ar.edu.unq.cookitbackend.security.WebSecurityConfig;
import ar.edu.unq.cookitbackend.service.IRecipes;
import ar.edu.unq.cookitbackend.service.InitService;
import ar.edu.unq.cookitbackend.service.impl.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import org.junit.Before;
import org.junit.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = RecipeController.class)
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private IRecipes recipeService;

    @MockBean
    private ObjectMapper objectMapper;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    private UserDetailsService jwtUserDetailsService;

    @MockBean
    private JwtRequestFilter jwtRequestFilter;

    private List<Recipe> recipes;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        recipes = new ArrayList<>();

        Recipe recipe1 = new Recipe();
        recipe1.setId(1L);
        recipe1.setName("Tarta de frutillas");
        recipe1.setDescription("Para aprovechar las frutillas de estación. En este caso la hice doble porque somos muchos, pero se puede hacer la mitad y sale una tarta redonda convencional.");
        recipe1.setName("Tarta de frutillas");
        recipe1.setImageUrl("https://img-global.cpcdn.com/recipes/424565f2d5a5103c/751x532cq70/tarta-de-frutillas-foto-principal.jpg");

        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);
        recipe2.setName("Salmon rosado con budin de verduras");
        recipe2.setDescription("Ideal para una comida rica y liviana. Ideal para una de estas noches frías que aun tenemos, ya que todo se hace al horno.");
        recipe2.setImageUrl("https://img-global.cpcdn.com/recipes/7da4e1e662bcf5b4/751x532cq70/salmon-rosado-con-budin-de-verduras-foto-principal.jpg");

        Recipe recipe3 = new Recipe();
        recipe3.setId(3L);
        recipe3.setName("Postre oreo");
        recipe3.setDescription("Oreo , dulce de leche, crema chica, Azúcar, Leche, Esencia de vainilla, Papel film(opcional).");
        recipe3.setImageUrl("https://img-global.cpcdn.com/recipes/9ec5cf84d2cb7938/751x532cq70/postre-oreo-foto-principal.jpg");

        recipes.add(recipe1);
        recipes.add(recipe2);
        recipes.add(recipe3);
    }
    
    @Test
    public void testShouldFetchRecipeById() throws Exception {
        Long recipeId = 1L;

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setName("Tarta de frutillas");
        recipe.setDescription("Para aprovechar las frutillas de estacion. En este caso la hice doble porque somos muchos, pero se puede hacer la mitad y sale una tarta redonda convencional.");
        recipe.setImageUrl("https://img-global.cpcdn.com/recipes/424565f2d5a5103c/751x532cq70/tarta-de-frutillas-foto-principal.jpg");

        given(recipeService.getRecipe(recipeId)).willReturn(recipe);

        mockMvc.perform(get("/api/recipes/{id}", recipeId).servletPath("/api"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(recipe.getName())))
                .andExpect(jsonPath("$.description", is(recipe.getDescription())))
                .andExpect(jsonPath("$.imageUrl", is(recipe.getImageUrl())));
    }

}
