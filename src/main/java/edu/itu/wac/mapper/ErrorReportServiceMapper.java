package edu.itu.wac.mapper;


import edu.itu.wac.entity.ErrorReport;
import edu.itu.wac.service.request.ErrorReportRequest;
import edu.itu.wac.service.response.ErrorReportResponse;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class ErrorReportServiceMapper extends ConfigurableMapper {
    protected void configure(MapperFactory factory) {
        factory.classMap(ErrorReportRequest.class, ErrorReport.class)
                .byDefault()
                .register();
        factory.classMap(ErrorReport.class, ErrorReportResponse.class)
                .byDefault()
                .register();
    }
}