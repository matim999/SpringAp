package app.DTO;

import java.util.Collection;
import java.util.stream.Collectors;

@FunctionalInterface
public interface BaseConverter<F,T> {

    T convert(F from);

    default Collection<T> convertAll(Collection<F> fElements){
        Collection<T> convertedElement =
                fElements.stream()
                        .map(this::convert)
                        .collect(Collectors.toList());

        return convertedElement;
    }

    default T convertAll(F Element){
        Element = (F) convert(Element);
        return (T) Element;
    }
}
