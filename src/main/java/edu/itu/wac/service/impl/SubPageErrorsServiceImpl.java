package edu.itu.wac.service.impl;

import edu.itu.wac.entity.SubPageErrors;
import edu.itu.wac.repository.ErrorRepository;
import edu.itu.wac.repository.SubPageErrorsRepository;
import edu.itu.wac.service.SubPageErrorsService;
import edu.itu.wac.service.response.SubPageErrorsResponse;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubPageErrorsServiceImpl implements SubPageErrorsService {
    private final SubPageErrorsRepository repository;
    private final ErrorRepository errorRepository;
    private final MapperFacade mapperFacade;

    @Autowired
    public SubPageErrorsServiceImpl(SubPageErrorsRepository repository,ErrorRepository errorRepository,
                                    @Qualifier(value = "errorReportServiceMapper") MapperFacade mapperFacade) {
        this.repository = repository;
        this.errorRepository = errorRepository;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public SubPageErrorsResponse findById(String id) {
        Optional<SubPageErrors> subPageErrorsOptional = repository.findById(id);
        SubPageErrorsResponse response = null;
        if (subPageErrorsOptional.isPresent()){
            response = mapperFacade.map(subPageErrorsOptional.get(),SubPageErrorsResponse.class);
        }
        return response;
    }
}