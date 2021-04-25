package edu.itu.wac.repository;

import edu.itu.wac.entity.ErrorReport;
import edu.itu.wac.entity.Website;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ErrorReportRepository extends JpaRepository<ErrorReport, String> {

    List<ErrorReport> findAll();
    List<ErrorReport> findAllByWebsiteIn(List<Website> websites);
}