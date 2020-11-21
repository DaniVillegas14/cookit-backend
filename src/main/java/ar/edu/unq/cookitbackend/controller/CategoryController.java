package ar.edu.unq.cookitbackend.controller;

import ar.edu.unq.cookitbackend.dto.request.CategoryRequestDto;
import ar.edu.unq.cookitbackend.dto.response.CategoryResponseDto;
import ar.edu.unq.cookitbackend.exception.NotFoundException;
import ar.edu.unq.cookitbackend.model.Category;
import ar.edu.unq.cookitbackend.service.IUserService;
import ar.edu.unq.cookitbackend.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private IUserService userService;

    @GetMapping("/user/{userId}/{name}")
    public ResponseEntity<String> addNewCategory(@PathVariable("userId") Long userId,
                                                 @PathVariable("name") String name) throws NotFoundException {
        userService.addNewCategory(userId, name);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public  ResponseEntity<List<CategoryResponseDto>> getCategories(@PathVariable("userId") Long userId) throws NotFoundException {
        List<Category> categories = userService.getCategories(userId);
        return new ResponseEntity<>(Converter.toCategoriesResponseDto(categories), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/add/{idCategory}/{idRecipe}")
    public ResponseEntity<String> addRecipeToCategory(@PathVariable("userId") Long userId,
                                                      @PathVariable("idCategory") Long idCategory,
                                                      @PathVariable("idRecipe") Long idRecipe) throws NotFoundException {
        userService.addRecipeToCategory(userId, idCategory, idRecipe);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/favorites/{idCategory}")
    public ResponseEntity<CategoryResponseDto> getFavoritesByRecipe(@PathVariable("userId") Long userId,
                                                                    @PathVariable("idCategory") Long idCategory) throws NotFoundException {
        Category category = userService.getFavoritesByCategory(userId, idCategory);
        return new ResponseEntity<>(Converter.toCategoryResponseDto(category), HttpStatus.OK);
    }
}
