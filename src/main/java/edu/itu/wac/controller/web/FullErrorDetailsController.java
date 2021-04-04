package edu.itu.wac.controller.web;


import edu.itu.wac.service.ErrorReportService;
import edu.itu.wac.service.SubPageErrorsService;
import edu.itu.wac.service.response.ErrorReportResponse;
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
import java.util.stream.Collectors;

@Controller
@Slf4j
public class FullErrorDetailsController {
    private final SubPageErrorsService subPageErrorsService;
    private final ErrorReportService errorReportService;

    public FullErrorDetailsController(SubPageErrorsService subPageErrorsService, ErrorReportService errorReportService) {
        this.subPageErrorsService = subPageErrorsService;
        this.errorReportService = errorReportService;
    }

    @RequestMapping("/full-error-details")
    @ResponseBody
    public ModelAndView serveHistoryPage(@RequestParam(required = false) String id, @RequestParam(required = false) String f, @RequestParam(required = false) String sort) {
        ModelAndView model = new ModelAndView();
        model.setViewName("full-error-details");
        List<ErrorResponse> errors;
        ErrorReportResponse errorReport = errorReportService.findById(id);
        errors = errorReport.getSubPageErrors()
                .stream()
                .flatMap(spe -> spe.getErrors().stream())
                .collect(Collectors.toList());
        if (!StringUtils.isEmpty(sort)) {
            switch (sort) {
                case "document_asc" :
                        errors.sort(Comparator.comparing(ErrorResponse::getDocument));
                        break;
                case "document_desc":
                        errors.sort(Comparator.comparing(ErrorResponse::getDocument).reversed());
                        break;
                case "subpage_asc":
                      errors.sort(Comparator.comparing(ErrorResponse::getSubPage));
                      break;
                default :
                    errors.sort(Comparator.comparing(ErrorResponse::getSubPage).reversed());
                }
            }

        model.addObject("errors", errors);
        model.addObject("errorReportId",errorReport.getId());
        return model;
    }

}
