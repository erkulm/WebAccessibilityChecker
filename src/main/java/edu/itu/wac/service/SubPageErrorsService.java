package edu.itu.wac.service;

import edu.itu.wac.service.response.SubPageErrorsResponse;
import org.springframework.stereotype.Service;

@Service
public interface SubPageErrorsService {
    SubPageErrorsResponse findById(String id);
}
