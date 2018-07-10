package app.DTO;

import app.entity.Category;
import app.entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements BaseConverter<Category, CategoryDto> {
    @Override
    public CategoryDto convert(Category from) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(from.getCategoryId());
        categoryDto.setName(from.getName());
        return categoryDto;
    }
}
