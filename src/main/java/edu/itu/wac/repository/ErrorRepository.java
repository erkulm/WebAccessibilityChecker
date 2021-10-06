package edu.itu.wac.repository;

import edu.itu.wac.entity.Error;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ErrorRepository extends JpaRepository<Error, String> {

    List<Error> findAllByWebsite_Address(String websiteAddress);
    List<Error> findAll();
    List<Error> findAllByWebsite_Id(String websiteId);
    List<Error> findAllByWebsite_IdAndTestCrDateIsAfter(String websiteId, LocalDateTime testDate);
    List<Error> findAllByWebsite_AddressContains(String websiteAddress);
}