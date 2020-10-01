package ar.edu.unq.cookitbackend.controller;

import ar.edu.unq.cookitbackend.dto.request.RecipeDto;
import ar.edu.unq.cookitbackend.model.Recipe;
import ar.edu.unq.cookitbackend.security.JwtAuthenticationEntryPoint;
import ar.edu.unq.cookitbackend.security.JwtRequestFilter;
import ar.edu.unq.cookitbackend.service.IRecipes;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IRecipes recipeService;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    private UserDetailsService jwtUserDetailsService;

    @MockBean
    private JwtRequestFilter jwtRequestFilter;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
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

    @Test
    public void testShouldCreateNewRecipe() throws Exception {

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setName("Tarta de frutillas");
        recipe.setDescription("Para aprovechar las frutillas de estacion. En este caso la hice doble porque somos muchos, pero se puede hacer la mitad y sale una tarta redonda convencional.");
        recipe.setImageUrl("https://img-global.cpcdn.com/recipes/424565f2d5a5103c/751x532cq70/tarta-de-frutillas-foto-principal.jpg");

        given(recipeService.createRecipe(any(RecipeDto.class))).willReturn(recipe);

        mockMvc.perform(post("/api/recipes/new").servletPath("/api")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipe)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(recipe.getName())))
                .andExpect(jsonPath("$.description", is(recipe.getDescription())))
                .andExpect(jsonPath("$.imageUrl", is(recipe.getImageUrl())));
    }

}
