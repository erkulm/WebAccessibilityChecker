package edu.itu.wac.service.impl;

import edu.itu.wac.entity.Error;
import edu.itu.wac.entity.ErrorReport;
import edu.itu.wac.entity.Website;
import edu.itu.wac.enums.ErrorExcelHeaders;
import edu.itu.wac.etc.LogExecutionTime;
import edu.itu.wac.job.Pa11yExecutor;
import edu.itu.wac.repository.ErrorReportRepository;
import edu.itu.wac.repository.ErrorRepository;
import edu.itu.wac.repository.SubPageErrorsRepository;
import edu.itu.wac.service.ErrorService;
import edu.itu.wac.service.WebsiteService;
import edu.itu.wac.service.request.ErrorRequest;
import edu.itu.wac.service.response.ErrorResponse;
import edu.itu.wac.service.response.WebsiteResponse;
import edu.itu.wac.util.ExcelUtil;
import edu.itu.wac.util.Pa11yUtil;
import ma.glasnost.orika.MapperFacade;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ErrorServiceImpl implements ErrorService {
    private final WebsiteService websiteService;
    private final ErrorRepository errorRepository;
    private final ErrorReportRepository errorReportRepository;
    private final SubPageErrorsRepository subPageErrorsRepository;
    private final Pa11yExecutor pa11yExecutor;
    private final MapperFacade mapperFacade;

    @Value("${test.min.day.difference}")
    Integer testDayDifference;

    @Autowired
    public ErrorServiceImpl(WebsiteService websiteService,
                            ErrorRepository errorRepository,
                            ErrorReportRepository errorReportRepository,
                            SubPageErrorsRepository subPageErrorsRepository,
                            Pa11yExecutor pa11yExecutor,
                            @Qualifier(value = "errorServiceMapper") MapperFacade mapperFacade) {
        this.websiteService = websiteService;
        this.errorRepository = errorRepository;
        this.errorReportRepository = errorReportRepository;
        this.subPageErrorsRepository = subPageErrorsRepository;
        this.pa11yExecutor = pa11yExecutor;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public ErrorResponse save(ErrorRequest errorRequest) {
        Error error = mapperFacade.map(errorRequest, Error.class);
        error = errorRepository.save(error);
        return mapperFacade.map(error, ErrorResponse.class);
    }

    @Override
    public List<ErrorResponse> getAll() {
        return mapperFacade.mapAsList(errorRepository.findAll(), ErrorResponse.class);
    }

    @Override
    public List<ErrorResponse> findByWebsiteAddress(String address) {
        WebsiteResponse website = websiteService.findByAddress(address);
        if (website != null) {
            List<Error> errors = errorRepository.findAllByWebsite_IdAndTestCrDateIsAfter(
                    website.getId(), LocalDateTime.now().minusDays(testDayDifference));
            return mapperFacade.mapAsList(errors, ErrorResponse.class);
        } else {
            return null;
        }
    }

    @Override
    public List<ErrorResponse> saveAll(List<ErrorRequest> errorRequests) {
        List<Error> errors = mapperFacade.mapAsList(errorRequests, Error.class);
        errors = errorRepository.saveAll(errors);
        return mapperFacade.mapAsList(errors, ErrorResponse.class);
    }

    @Override
    @LogExecutionTime
    public List<ErrorResponse> generateReport(String address) {
        WebsiteResponse websiteResponse = websiteService.findByAddress(address);
        if (websiteResponse == null) {
            websiteResponse = websiteService.createNewWebsiteFromAddress(address);
        }
        List<ErrorResponse> errorResponses;
        if (websiteResponse.getLatestTestDate() == null
                || websiteResponse.getLatestTestDate().plusDays(testDayDifference).isBefore(LocalDateTime.now())) {
            ErrorReport errorReport = Pa11yUtil.runPa11y(
                    mapperFacade.map(websiteResponse, Website.class), "");

            List<Error> errors = errorReport.getSubPageErrors()
                    .stream()
                    .flatMap(spe -> spe.getErrors().stream())
                    .collect(Collectors.toList());
            errorRepository.saveAll(errors);
            subPageErrorsRepository.saveAll(errorReport.getSubPageErrors());
            errorResponses = mapperFacade.mapAsList(errors, ErrorResponse.class);
            errorReportRepository.save(errorReport);
            websiteService.updateLatestTestDate(address);
        } else {
            errorResponses = findByWebsiteAddress(address);
        }
        return errorResponses;
    }

    @Override
    @LogExecutionTime
    public List<ErrorResponse> generateDeepReport(String address) {
        WebsiteResponse websiteResponse = websiteService.findByAddress(address);
        if (websiteResponse == null) {
            websiteResponse = websiteService.createNewWebsiteFromAddress(address);
        }
        List<ErrorResponse> errorResponses;
        if (websiteResponse.getLatestTestDate() == null
                || websiteResponse.getLatestTestDate().plusDays(testDayDifference).isBefore(LocalDateTime.now())) {
            ErrorReport errorReport = pa11yExecutor.executePally(
                    mapperFacade.map(websiteResponse, Website.class));

            List<Error> errors = errorReport.getSubPageErrors()
                    .stream()
                    .flatMap(spe -> spe.getErrors().stream())
                    .collect(Collectors.toList());
            errorRepository.saveAll(errors);
            subPageErrorsRepository.saveAll(errorReport.getSubPageErrors());
            errorReport.setWebsite(mapperFacade.map(websiteResponse, Website.class));
            errorResponses = mapperFacade.mapAsList(errors, ErrorResponse.class);
            errorReportRepository.save(errorReport);
            websiteService.updateLatestTestDate(address);
        } else {
            errorResponses = findByWebsiteAddress(address);
        }
        return errorResponses;
    }

    @Override
    public Workbook generateExcel(List<ErrorResponse> items) {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("Special Fare Rates");
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);

        Map<String, CellStyle> styles = ExcelUtil.createExcelStyle(wb);

        for (int i = 0; i < 20; i++) {
            sheet.setColumnWidth(i, 5000);
        }

        int rowNum = createHeadersForErrorReport(sheet, styles);

        createCellsForError(sheet, rowNum, styles, items);
        return wb;
    }

    private void createCellsForError(Sheet sheet, int rowNum, Map<String, CellStyle> styles,
                                     List<ErrorResponse> errorList) {
        CellStyle cellStyle = styles.get("cell");
        for (ErrorResponse errorResponse : errorList) {

            Row row = sheet.createRow(++rowNum);
            int inx = 0;
            DateTimeFormatter sdf = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);

            Cell website = row.createCell(inx++);
            website.setCellStyle(cellStyle);
            website.setCellValue(new HSSFRichTextString(errorResponse.getWebsite().getAddress()));

            Cell subPage = row.createCell(inx++);
            subPage.setCellStyle(cellStyle);
            subPage.setCellValue(new HSSFRichTextString(errorResponse.getSubPage()));

            Cell description = row.createCell(inx++);
            description.setCellStyle(cellStyle);
            description.setCellValue(new HSSFRichTextString(errorResponse.getErrorDesc()));

            Cell scene = row.createCell(inx++);
            scene.setCellStyle(cellStyle);
            scene.setCellValue(new HSSFRichTextString(errorResponse.getErrorScene()));

            Cell address = row.createCell(inx++);
            address.setCellStyle(cellStyle);
            address.setCellValue(
                    new HSSFRichTextString(errorResponse.getErrorAddress()));

            Cell document = row.createCell(inx);
            document.setCellStyle(cellStyle);
            document.setCellValue(
                    new HSSFRichTextString(errorResponse.getDocument() != null ? errorResponse.getDocument() : ""));
        }
    }

    private int createHeadersForErrorReport(Sheet sheet, Map<String, CellStyle> styles) {
        CellStyle headerStyle = styles.get("header");
        CellStyle titleStyle = styles.get("title");
        int rowNum = 0;
        Row titleRow = sheet.createRow(rowNum);
        titleRow.setHeightInPoints(45);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("ERROR REPORT");
        titleCell.setCellStyle(titleStyle);
        // range of style for title, uses excel notation
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$U$1"));

        rowNum++;
        Row headerRow = sheet.createRow(rowNum);
        headerRow.setHeightInPoints(40);

        int inx = 0;

        ErrorExcelHeaders[] headerList = ErrorExcelHeaders.values();

        for (ErrorExcelHeaders downloadHeaders : headerList) {
            Cell cellHeader = headerRow.createCell(inx++);
            cellHeader.setCellStyle(headerStyle);
            cellHeader.setCellValue(new HSSFRichTextString(downloadHeaders.toString()));
        }

        return rowNum;
    }

}
