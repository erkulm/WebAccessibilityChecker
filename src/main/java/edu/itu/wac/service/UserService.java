package edu.itu.wac.service;

import edu.itu.wac.service.request.UserRequest;
import edu.itu.wac.service.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    UserResponse findByUsername(String username);
    UserResponse save(UserRequest userRequest);
    UserResponse deleteById(String id);
}
