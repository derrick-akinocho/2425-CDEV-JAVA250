package com.example.demo.service.Articles;

import com.example.demo.dto.ArticleDto;
import com.example.demo.service.ArticleService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

@Service
public class ExportXlsxServiceArticle {

    @Autowired
    private ArticleService articleService;

    public static final int COL_LIBELLE = 0;
    public static final int COL_PRIX = 1;
    public static final int COL_IMAGE_URL = 2;
    public static final int COL_QUANTITE = 3;


    private void createHeaderRow(Sheet sheet, CellStyle styleHeader) {
        Row headerRow = sheet.createRow(0);

        createCell(headerRow, COL_LIBELLE, "Nom", styleHeader);
        createCell(headerRow, COL_PRIX, "Prenom", styleHeader);
        createCell(headerRow, COL_IMAGE_URL, "Image", styleHeader);
        createCell(headerRow, COL_QUANTITE, "Quantite", styleHeader);
    }

    private void createCell(Row row, int columnIndex, String value, CellStyle style) {
        Cell cell = row.createCell(columnIndex);
        cell.setCellStyle(style);
        cell.setCellValue(value);
    }

    private void exportRow(Row row, ArticleDto articleDtos) {

        createCell(row, COL_LIBELLE, articleDtos.getLibelle(), null);
        createCell(row, COL_PRIX, String.valueOf(articleDtos.getPrix()), null);
        createCell(row, COL_IMAGE_URL, articleDtos.getImageUrl(), null);
        createCell(row, COL_QUANTITE, String.valueOf(articleDtos.getQuantite()), null);
    }

    private CellStyle styleColor(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        setBorderColor(style);

        Font font = workbook.createFont();
        font.setColor(IndexedColors.GREEN.getIndex());
        style.setFont(font);
        return style;
    }

    private void setBorderColor(CellStyle style) {
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBottomBorderColor(IndexedColors.BLUE.getIndex());

        style.setBorderTop(BorderStyle.MEDIUM);
        style.setTopBorderColor(IndexedColors.BLUE.getIndex());

        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setLeftBorderColor(IndexedColors.BLUE.getIndex());

        style.setBorderRight(BorderStyle.MEDIUM);
        style.setRightBorderColor(IndexedColors.BLUE.getIndex());
    }

    public void exportData(ServletOutputStream outputStream) throws IOException {

        Iterable<ArticleDto> articleDtos = articleService.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Articles");

        CellStyle styleHeader = styleColor(workbook);
        createHeaderRow(sheet, styleHeader);
        int rowIndex = 1;

        for (ArticleDto articleDto : articleDtos) {

            Row row = sheet.createRow(rowIndex++);
            exportRow(row, articleDto);
        }

        workbook.write(outputStream);
        workbook.close();
    }
}
