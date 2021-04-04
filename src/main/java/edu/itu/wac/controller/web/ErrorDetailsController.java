package edu.itu.wac.controller.web;


import edu.itu.wac.service.ErrorReportService;
import edu.itu.wac.service.SubPageErrorsService;
import edu.itu.wac.service.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;

@Controller
@Slf4j
public class ErrorDetailsController {
    private final SubPageErrorsService subPageErrorsService;
    private final ErrorReportService errorReportService;

    public ErrorDetailsController(SubPageErrorsService subPageErrorsService, ErrorReportService errorReportService) {
        this.subPageErrorsService = subPageErrorsService;
        this.errorReportService = errorReportService;
    }

    @RequestMapping("/error-details")
    @ResponseBody
    public ModelAndView serveHistoryPage(@RequestParam(required = false) String id, @RequestParam(required = false) String f, @RequestParam(required = false) String sort) {
        ModelAndView model = new ModelAndView();
        model.setViewName("error-details");
        List<ErrorResponse> errors;
        errors = subPageErrorsService.findById(id).getErrors();
        if (!StringUtils.isEmpty(sort)) {
            switch (sort) {
                case "document_asc" :
                    errors.sort(Comparator.comparing(ErrorResponse::getDocument));
                    break;
                case "document_desc" :
                    errors.sort(Comparator.comparing(ErrorResponse::getDocument).reversed());
                    break;
                default :
                    errors.sort(Comparator.comparing(ErrorResponse::getDocument));
            }
        }
        model.addObject("errors", errors);
        model.addObject("subPageErrorId", id);
        return model;
    }

}
