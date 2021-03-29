package edu.itu.wac.controller.rest;

import edu.itu.wac.service.UserService;
import edu.itu.wac.service.request.UserRequest;
import edu.itu.wac.service.response.UserResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
    private final UserService userService;
    private final MapperFacade mapperFacade;

    @Autowired
    public UserRestController(UserService userService,
                              @Qualifier(value = "userServiceMapper") MapperFacade mapperFacade) {
        this.userService = userService;
        this.mapperFacade = mapperFacade;
    }

    @ApiOperation(value = "Insert User", notes = "User definition<br/>")
    @GetMapping("insert-user")
    public ResponseEntity<UserResponse>
    insert(@ApiParam(value = "FareRate save request") @RequestParam String username, @RequestParam String password) {

        if (!StringUtils.isEmpty(username)
                && !StringUtils.isEmpty(password)) {

            UserRequest userRequest = new UserRequest();
            userRequest.setPassword(password);
            userRequest.setUsername(username);

            UserResponse userResponse = userService.save(userRequest);
            return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
