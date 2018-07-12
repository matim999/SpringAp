package app.DTO.converter;

import app.DTO.requestDTO.CategoryDtoRequest;
import app.DTO.responseDTO.FilmDto;
import app.DTO.responseDTO.CategoryDto;
import app.entity.Category;
import app.entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class CategoryConverter implements BaseConverter<Category, CategoryDto>, ToBaseConverter<CategoryDtoRequest, CategoryDto> {
    private final BaseConverter<Film, FilmDto> filmConverter;

    @Autowired
    public CategoryConverter(BaseConverter<Film, FilmDto> filmConverter) {
        this.filmConverter = filmConverter;
    }

    @Override
    public CategoryDto convert(Category from) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(from.getCategoryId());
        categoryDto.setName(from.getName());
        categoryDto.setFilms(filmConverter.convertAll(from.getFilms()));
        return categoryDto;
    }

    @Override
    public CategoryDto convertToBase(CategoryDtoRequest from) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(from.getName());
        categoryDto.setFilms(new ArrayList<>());
        return categoryDto;
    }
}
