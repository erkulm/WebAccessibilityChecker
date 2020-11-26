package edu.itu.wac.mapper;


import edu.itu.wac.entity.Website;
import edu.itu.wac.service.request.WebsiteRequest;
import edu.itu.wac.service.response.WebsiteResponse;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class WebsiteServiceMapper extends ConfigurableMapper {
    protected void configure(MapperFactory factory) {
        factory.classMap(WebsiteRequest.class, Website.class)
                .byDefault()
                .register();
        factory.classMap(Website.class, WebsiteResponse.class)
                .byDefault()
                .register();
    }
}