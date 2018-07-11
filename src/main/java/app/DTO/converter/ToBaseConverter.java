package app.DTO.converter;

import java.util.Collection;
import java.util.stream.Collectors;

@FunctionalInterface
public interface ToBaseConverter<F,T> {

    T convertToBase(F from);

    default Collection<T> convertAllToBase(Collection<F> fElements){

        return fElements.stream()
                .map(this::convertToBase)
                .collect(Collectors.toList());
    }

    default T convertAllToBase(F Element){
        Element = (F) convertToBase(Element);
        return (T) Element;
    }
}
