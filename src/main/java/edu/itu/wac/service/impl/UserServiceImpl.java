package edu.itu.wac.service.impl;

import edu.itu.wac.entity.User;
import edu.itu.wac.model.UserPrincipal;
import edu.itu.wac.repository.UserRepository;
import edu.itu.wac.service.UserService;
import edu.itu.wac.service.request.UserRequest;
import edu.itu.wac.service.response.UserResponse;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final MapperFacade mapperFacade;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(
      UserRepository userRepository,@Lazy PasswordEncoder passwordEncoder,
      @Qualifier(value = "userServiceMapper") MapperFacade mapperFacade) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.mapperFacade = mapperFacade;
  }

  @Override
  public UserResponse findByUsername(String username) {
    Optional<User> user = userRepository.findUserByUsername(username);
    return user.map(value -> mapperFacade.map(value, UserResponse.class)).orElse(null);
  }

  @Override
  public UserResponse save(UserRequest userRequest) {
    userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
    User savedUser = userRepository.save(mapperFacade.map(userRequest, User.class));
    return mapperFacade.map(savedUser, UserResponse.class);
  }

  @Override
  public UserResponse deleteById(String id) {
    if (id != null) {
      Optional<User> userToDelete = userRepository.findById(id);
      if (userToDelete.isPresent()) {
        userRepository.delete(userToDelete.get());
        return mapperFacade.map(userToDelete.get(), UserResponse.class);
      }
    }
    return null;
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    UserResponse userResponse = findByUsername(s);

    if (userResponse != null) {
      return new UserPrincipal(userResponse);
    }
    return null;
  }
}
