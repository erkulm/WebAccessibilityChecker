package edu.itu.wac.repository;

import edu.itu.wac.entity.WebsiteCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WebsiteCategoryRepository extends JpaRepository<WebsiteCategory, String> {

    Optional<WebsiteCategory> findByName(String name);
    List<WebsiteCategory> findAll();
}