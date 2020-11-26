package edu.itu.wac.mapper;


import edu.itu.wac.entity.WebsiteCategory;
import edu.itu.wac.service.request.WebsiteCategoryRequest;
import edu.itu.wac.service.response.WebsiteCategoryResponse;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class WebsiteCategoryServiceMapper extends ConfigurableMapper {
    protected void configure(MapperFactory factory) {
        factory.classMap(WebsiteCategoryRequest.class, WebsiteCategory.class)
                .byDefault()
                .register();
        factory.classMap(WebsiteCategory.class, WebsiteCategoryResponse.class)
                .byDefault()
                .register();
    }
}