package edu.itu.wac.repository;

import edu.itu.wac.entity.SubPageErrors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubPageErrorsRepository extends JpaRepository<SubPageErrors, String> {

    List<SubPageErrors> findAll();
    List<SubPageErrors> findAllBySubPage(String websiteId);
    Optional<SubPageErrors> findById(String id);
}