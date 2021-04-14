package edu.itu.wac.service;

import edu.itu.wac.service.request.PermissionRequest;
import edu.itu.wac.service.response.PermissionResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PermissionService {
    PermissionResponse save(PermissionRequest permissionRequest);

    PermissionResponse getById(String id);

    List<PermissionResponse> getAll();
    PermissionResponse deleteById(String id);
}
