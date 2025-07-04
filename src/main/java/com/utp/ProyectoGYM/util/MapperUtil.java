package com.utp.ProyectoGYM.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {
    private final ModelMapper modelMapper = new ModelMapper();

    public <D, T> D map(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }
}

