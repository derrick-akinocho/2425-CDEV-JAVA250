package com.example.demo.service.Clients;

import com.example.demo.dto.ClientDto;
import com.example.demo.service.ClientService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

@Service
public class ExportXlsxServiceClient {

    @Autowired
    private ClientService clientService;

    public static final int COL_NOM = 0;
    public static final int COL_PRENOM = 1;
    public static final int COL_AGE = 2;

    private void createHeaderRow(Sheet sheet, CellStyle styleHeader) {
        Row headerRow = sheet.createRow(0);

        createCell(headerRow, COL_NOM, "Nom", styleHeader);
        createCell(headerRow, COL_PRENOM, "Prenom", styleHeader);
        createCell(headerRow, COL_AGE, "Age", styleHeader);
    }

    private void createCell(Row row, int columnIndex, String value, CellStyle style) {
        Cell cell = row.createCell(columnIndex);
        cell.setCellStyle(style);
        cell.setCellValue(value);
    }

    private void exportRow(Row row, ClientDto clientDtos, CellStyle style) {
        createCell(row, COL_NOM, clientDtos.getNom(), style);
        createCell(row, COL_PRENOM, clientDtos.getPrenom(), style);
        createCell(row, COL_AGE, (clientDtos.getAge() + " ans"), style);
    }

    public void exportData(ServletOutputStream outputStream) throws IOException {
        Iterable<ClientDto> clientDtos = clientService.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Clients");

        CellStyle styleHeader = styleColor(workbook);
        createHeaderRow(sheet, styleHeader);

        CellStyle rowStyle = createRowStyle(workbook);
        int rowIndex = 1;

        for (ClientDto clientDto : clientDtos) {
            Row row = sheet.createRow(rowIndex++);
            exportRow(row, clientDto, rowStyle);
        }

        workbook.write(outputStream);
        workbook.close();
    }

    private CellStyle styleColor(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        setBorderColor(style);

        Font font = workbook.createFont();
        font.setColor(IndexedColors.ROSE.getIndex());
        style.setFont(font);

        return style;
    }

    private CellStyle createRowStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        setBorderColor(style);

        return style;
    }

    private void setBorderColor(CellStyle style) {
        style.setBorderBottom(BorderStyle.THICK);
        style.setBottomBorderColor(IndexedColors.BLUE.getIndex());

        style.setBorderTop(BorderStyle.THICK);
        style.setTopBorderColor(IndexedColors.BLUE.getIndex());

        style.setBorderLeft(BorderStyle.THICK);
        style.setLeftBorderColor(IndexedColors.BLUE.getIndex());

        style.setBorderRight(BorderStyle.THICK);
        style.setRightBorderColor(IndexedColors.BLUE.getIndex());
    }
}
