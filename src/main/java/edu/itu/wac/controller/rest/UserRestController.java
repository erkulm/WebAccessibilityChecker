package edu.itu.wac.controller.rest;

import edu.itu.wac.service.UserService;
import edu.itu.wac.service.request.UserRequest;
import edu.itu.wac.service.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("insert")
    public ResponseEntity<UserResponse> insert(@RequestBody UserRequest userRequest) {

        if (!StringUtils.isEmpty(userRequest.getUsername())
                && !StringUtils.isEmpty(userRequest.getPassword())) {
            UserResponse userResponse = userService.save(userRequest);
            return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get/{id}")
    public ResponseEntity<UserResponse> get(@PathVariable String id) {
        UserResponse user = userService.getById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("get-all")
    public ResponseEntity<List<UserResponse>> getAll() {
        List<UserResponse> userResponses = userService.getAll();
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<UserResponse>
    update(@Valid @RequestBody UserRequest userRequest) {

        UserResponse userResponse = userService.getById(userRequest.getId());
        if (userResponse==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!StringUtils.isEmpty(userRequest.getPassword()) && !StringUtils.isEmpty(userRequest.getUsername())) {

            UserResponse newUserResponse = userService.save(userRequest);
            return new ResponseEntity<>(newUserResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String id) {
        UserResponse user = userService.getById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
