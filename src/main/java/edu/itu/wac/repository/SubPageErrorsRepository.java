package edu.itu.wac.repository;

import edu.itu.wac.entity.SubPageErrors;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SubPageErrorsRepository extends MongoRepository<SubPageErrors, String> {

    List<SubPageErrors> findAll();
    List<SubPageErrors> findAllBySubPage(String websiteId);
    Optional<SubPageErrors> findById(String id);
}