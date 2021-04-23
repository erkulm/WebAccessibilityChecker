package edu.itu.wac.mapper;


import edu.itu.wac.entity.Error;
import edu.itu.wac.service.request.ErrorRequest;
import edu.itu.wac.service.response.ErrorResponse;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class ErrorServiceMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(ErrorRequest.class, Error.class)
                .byDefault()
                .register();
        factory.classMap(Error.class, ErrorResponse.class)
                .byDefault()
                .register();
    }
}