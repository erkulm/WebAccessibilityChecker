package edu.itu.wac.service.impl;

import edu.itu.wac.entity.Permission;
import edu.itu.wac.repository.PermissionRepository;
import edu.itu.wac.service.PermissionService;
import edu.itu.wac.service.request.PermissionRequest;
import edu.itu.wac.service.response.PermissionResponse;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {
  private final PermissionRepository permissionRepository;
  private final MapperFacade mapperFacade;

  @Autowired
  public PermissionServiceImpl(
      PermissionRepository permissionRepository,
      @Qualifier(value = "userServiceMapper") MapperFacade mapperFacade) {
    this.permissionRepository = permissionRepository;
    this.mapperFacade = mapperFacade;
  }

  @Override
  public PermissionResponse save(PermissionRequest permissionRequest) {
    Permission permission =
        permissionRepository.save(mapperFacade.map(permissionRequest, Permission.class));
    return mapperFacade.map(permission, PermissionResponse.class);
  }

  @Override
  public PermissionResponse deleteById(String id) {
    if (id != null) {
      Optional<Permission> permissionOptional = permissionRepository.findById(id);
      if (permissionOptional.isPresent()) {
        permissionRepository.delete(permissionOptional.get());
        return mapperFacade.map(permissionOptional.get(), PermissionResponse.class);
      }
    }
    return null;
  }

  @Override
  public PermissionResponse getById(String id) {
    Optional<Permission> optionalUser = permissionRepository.findById(id);
    return optionalUser.map(permission -> mapperFacade.map(permission, PermissionResponse.class)).orElse(null);
  }

  @Override
  public List<PermissionResponse> getAll() {
    return mapperFacade.mapAsList(permissionRepository.findAll(), PermissionResponse.class);
  }
}
