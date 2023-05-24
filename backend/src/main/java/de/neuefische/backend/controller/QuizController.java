package de.neuefische.backend.controller;

import de.neuefische.backend.model.CategoryList;
import de.neuefische.backend.service.QuizService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class QuizController {

    private final QuizService service = new QuizService();

    @GetMapping("/categories")
    public CategoryList getCategories() {
        return service.getCategories();
    }

}
