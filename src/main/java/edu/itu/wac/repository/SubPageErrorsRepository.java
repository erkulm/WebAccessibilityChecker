package edu.itu.wac.repository;

import edu.itu.wac.entity.ErrorReport;
import edu.itu.wac.entity.SubPageErrors;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SubPageErrorsRepository extends MongoRepository<SubPageErrors, String> {

    List<SubPageErrors> findAll();
    List<SubPageErrors> findAllBySubPage(String websiteId);
}