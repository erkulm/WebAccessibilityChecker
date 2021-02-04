package edu.itu.wac.repository;

import edu.itu.wac.entity.Website;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface WebsiteRepository extends MongoRepository<Website, String> {

    Optional<Website> findByAddress(String address);
    List<Website> findByAddressIn(List<String> adresses);
    List<Website> findAll();
}