package com.bruno.spring.Project.Spring.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class ObjectMapper {

    private static final Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    // Class recebe o objeto de origem e a classe de destino e retorna o objeto mapeado para a classe de destino

    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    // Class recebe uma lista de objetos de origem e a classe de destino e retorna uma lista de objetos mapeados para a classe de destino usando Dozer
    public static <O, D> java.util.List<D> parseListObjects(List<O> origin, Class<D> destination) {
        List<D> destinationObjects = new ArrayList<>();

        for (O object : origin) {
            destinationObjects.add(mapper.map(object, destination));
        }
        return destinationObjects;
    }
}
