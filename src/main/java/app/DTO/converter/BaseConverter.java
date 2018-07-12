package app.DTO.converter;

import java.util.Collection;
import java.util.stream.Collectors;

@FunctionalInterface
public interface BaseConverter<F,T> {

    T convert(F from);

    default Collection<T> convertAll(Collection<F> fElements){

        return fElements.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    default T convertAll(F Element){
        Element = (F) convert(Element);
        return (T) Element;
    }

}
