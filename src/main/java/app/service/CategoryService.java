package app.service;

import app.ErrorCode;
import app.DTO.converter.BaseConverter;
import app.DTO.converter.ToBaseConverter;
import app.DTO.requestDTO.CategoryDtoRequest;
import app.DTO.responseDTO.CategoryDto;
import app.entity.Category;
import app.exceptions.ConflictException;
import app.exceptions.MyNotFoundException;
import app.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final BaseConverter<Category, CategoryDto> categoryConverter;
    private final ToBaseConverter<CategoryDtoRequest, CategoryDto> categoryRequestConverter;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, BaseConverter<Category, CategoryDto> categoryConverter, ToBaseConverter<CategoryDtoRequest, CategoryDto> categoryRequestConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryConverter = categoryConverter;
        this.categoryRequestConverter = categoryRequestConverter;
    }


    public void addNewCategory(CategoryDtoRequest categoryDtoRequest)
    {
        Collection<Category> collection = categoryRepository.findAllByName(categoryDtoRequest.getName()).orElse(new ArrayList<>());
        if (collection.isEmpty()){
            categoryRepository.save(new Category(categoryRequestConverter.convertAllToBase(categoryDtoRequest)));
            return;
        }
        CategoryDto categoryDto = categoryRequestConverter.convertAllToBase(categoryDtoRequest);
        Collection<CategoryDto> category = categoryConverter.convertAll(collection);
        category.forEach(a -> {
            if (a.equals(categoryDto))
                throw new ConflictException("Category with given name already exists", ErrorCode.DIFFERENT);
        });
        categoryRepository.save(new Category(categoryRequestConverter.convertAllToBase(categoryDtoRequest)));
    }

    public void deleteCategoryById(int id)
    {
        categoryRepository.findById(id).orElseThrow(() -> new MyNotFoundException("No Category With Given Id", ErrorCode.DIFFERENT));
        categoryRepository.deleteById(id);
    }
}
