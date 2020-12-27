package edu.itu.wac.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.springframework.util.StringUtils;

public class ExcelUtil {

    private ExcelUtil() {
    }

    public static Map<String, CellStyle> createExcelStyle(Workbook wb) {
        Map<String, CellStyle> styles = new HashMap<>();

        // Create Title Style
        CellStyle style;
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short)18);
        titleFont.setBold(true);
        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(titleFont);
        styles.put("title", style);

        // Create Header Style
        Font headerFont = wb.createFont();
        headerFont.setFontHeightInPoints((short)11);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(headerFont);
        style.setWrapText(true);
        styles.put("header", style);

        // Create Cell Style
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styles.put("cell", style);
        return styles;
    }

    public static String getStringCellValue(Row row, int inx) {
        String value = null;
        if (row.getCell(inx) != null) {
            if (row.getCell(inx).getCellType() == CellType.STRING)
                value = row.getCell(inx).getStringCellValue().toUpperCase().trim();
            else {
                value = String.valueOf(BigDecimal.valueOf(row.getCell(inx).getNumericCellValue()).toPlainString());
                if (value.contains(".")) {
                    value = value.substring(0, value.indexOf("."));
                }
                if ("0".equals(value)) {
                    value = "";
                }
            }
        }
        return value;
    }

    public static LocalDate getDateCellValue(Row row, int inx) {
        return row.getCell(inx) != null ? convertToLocalDateViaInstant(row.getCell(inx).getDateCellValue()) : null;
    }

    public static BigDecimal getNumericCellValue(Row row, int inx) {
        BigDecimal value = null;
        if (row.getCell(inx) != null) {
            if (row.getCell(inx).getCellType() == CellType.NUMERIC)
                value = BigDecimal.valueOf(row.getCell(inx).getNumericCellValue());
            else {
                String stringCellValue = row.getCell(inx).getStringCellValue();
                if (StringUtils.isEmpty(stringCellValue)) {
                    value = new BigDecimal("0");
                } else {
                    value = new BigDecimal(stringCellValue);
                }
            }
        }
        return value;
    }

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static boolean checkIfRowIsEmpty(Row row) {
        if (row == null) {
            return true;
        }
        if (row.getLastCellNum() <= 0) {
            return true;
        }
        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellType() != CellType.BLANK && !StringUtils.isEmpty(cell.toString().trim())) {
                return false;
            }
        }
        return true;
    }
}

