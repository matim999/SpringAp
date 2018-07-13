package app.DTO.converter;

import app.DTO.responseDTO.CategoryDtoNoFilm;
import app.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryNoFilmConverter implements BaseConverter<Category, CategoryDtoNoFilm> {


    @Override
    public CategoryDtoNoFilm convert(Category from) {
        CategoryDtoNoFilm categoryDto = new CategoryDtoNoFilm();
        categoryDto.setCategoryId(from.getCategoryId());
        categoryDto.setName(from.getName());
        return categoryDto;
    }
}
