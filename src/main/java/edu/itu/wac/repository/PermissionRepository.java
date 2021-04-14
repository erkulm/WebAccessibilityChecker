package edu.itu.wac.repository;

import edu.itu.wac.entity.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PermissionRepository extends MongoRepository<Permission, String> {
}