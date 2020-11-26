package edu.itu.wac.repository;

import edu.itu.wac.entity.Error;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ErrorReportRepository extends MongoRepository<Error, String> {

    List<Error> findAllByWebsite_Address(String websiteAddress);
    List<Error> findAll();
    List<Error> findAllByWebsite_Id(String websiteId);
    List<Error> findAllByWebsite_AddressContains(String websiteAddress);
}