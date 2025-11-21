package ua.hudyma.mapper;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class EntityUtilMapper {

    public <E> List<String> getEntityFieldList(
            List<E> entityList,
                      Function<E, String> mapper) {
        return entityList.stream().map(mapper).toList();
    }

}
