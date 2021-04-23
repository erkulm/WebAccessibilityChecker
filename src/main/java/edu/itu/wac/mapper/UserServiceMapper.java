package edu.itu.wac.mapper;


import edu.itu.wac.entity.Permission;
import edu.itu.wac.entity.User;
import edu.itu.wac.service.request.UserRequest;
import edu.itu.wac.service.response.PermissionResponse;
import edu.itu.wac.service.response.UserResponse;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class UserServiceMapper extends ConfigurableMapper {
    protected void configure(MapperFactory factory) {
        factory.classMap(UserRequest.class, User.class)
                .byDefault()
                .register();
        factory.classMap(User.class, UserResponse.class)
                .byDefault()
                .register();

        factory.classMap(Permission.class, PermissionResponse.class)
                .byDefault()
                .register();
    }
}