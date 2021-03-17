package edu.itu.wac.repository;

import edu.itu.wac.entity.ErrorReport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ErrorReportRepository extends MongoRepository<ErrorReport, String> {

    List<ErrorReport> findAllByWebsite_Address(String websiteAddress);
    List<ErrorReport> findAll();
    List<ErrorReport> findAllByWebsite_Id(String websiteId);
    List<ErrorReport> findAllByWebsite_IdAndCreatedDateAfter(String websiteId, LocalDateTime testDate);
    List<ErrorReport> findAllByWebsite_AddressContains(String websiteAddress);

}