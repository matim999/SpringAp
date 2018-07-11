package app.DTO.converter;

import app.entity.Mpaa_rating;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MpaaRatingConverter implements AttributeConverter<Mpaa_rating, String> {
    @Override
    public String convertToDatabaseColumn(Mpaa_rating rating) {
        return rating.getRating();
    }

    @Override
    public Mpaa_rating convertToEntityAttribute(String dbData) {
        return Mpaa_rating.fromDbToEntity(dbData);
    }
}
