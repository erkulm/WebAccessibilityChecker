package edu.itu.wac.repository;

import edu.itu.wac.entity.WebsiteCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface WebsiteCategoryRepository extends MongoRepository<WebsiteCategory, String> {

    Optional<WebsiteCategory> findByName(String name);
    List<WebsiteCategory> findAll();
}