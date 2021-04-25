package edu.itu.wac.repository;

import edu.itu.wac.entity.Website;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WebsiteRepository extends JpaRepository<Website, String> {

    Optional<Website> findByAddress(String address);
    List<Website> findByAddressContaining(String address);
    List<Website> findByAddressIn(List<String> addresses);
    List<Website> findAll();
}