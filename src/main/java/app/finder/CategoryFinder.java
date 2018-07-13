package app.finder;

import app.entity.Category;
import app.exceptions.MyNotFoundException;
import app.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static app.ErrorCode.DIFFERENT;

@Component
public class CategoryFinder {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryFinder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(int id) {
        return categoryRepository.findById(id).orElseThrow(() -> new MyNotFoundException("Category with given Id not found", DIFFERENT));
    }

    public List<Category> findAllCategoryByName(String name) {
        return categoryRepository.findAllByName(name).orElseThrow(() -> new MyNotFoundException("Category with given name not found", DIFFERENT));
    }
}
