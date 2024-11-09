package com.example.demo.service.Factures;

import com.example.demo.dto.ClientDto;
import com.example.demo.dto.FactureDto;
import com.example.demo.dto.LigneFactureDto;
import com.example.demo.entity.ClientEntity;
import com.example.demo.service.ClientService;
import com.example.demo.service.FactureService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
public class ExportXlsxServiceFacture {

    @Autowired
    private FactureService factureService;

    @Autowired
    private ClientService clientService;

    public static final int COL_0 = 0;
    public static final int COL_1 = 1;

    private void createClientInfoRow(Sheet sheet, int rowIndex, String label, String value, CellStyle style) {
        Row row = sheet.createRow(rowIndex);
        createCell(row, COL_0, label, style);
        createCell(row, COL_1, value, style);
    }

    private void createCell(Row row, int columnIndex, String value, CellStyle style) {
        Cell cell = row.createCell(columnIndex);
        cell.setCellStyle(style);
        cell.setCellValue(value);
    }

    public void exportData(ServletOutputStream outputStream) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        CellStyle rowStyle = createRowStyle(workbook);

        for (ClientDto clientEntity : clientService.findAll()) {

            String sheetName = clientEntity.getNom() + " " + clientEntity.getPrenom();
            Sheet sheet = workbook.createSheet(sheetName);

            int rowIndex = 0;

            createClientInfoRow(sheet, rowIndex++, "Nom :", clientEntity.getNom(), rowStyle);
            createClientInfoRow(sheet, rowIndex++, "Prénom :", clientEntity.getPrenom(), rowStyle);
            createClientInfoRow(sheet, rowIndex++, "Année de naissance :",
                    String.valueOf( LocalDate.now().getYear() - clientEntity.getAge()), rowStyle);

            // factures d'un client
            List<FactureDto> facturesClient = factureService.findByClientId(clientEntity.getId());

            int nombreTotalFactures = facturesClient.size();
            int totalLignesFactures = facturesClient.stream().mapToInt(facture -> facture.getLigneFactures().size()).sum();

            createClientInfoRow(sheet, rowIndex++, nombreTotalFactures + " facture(s) :",
                    String.valueOf(totalLignesFactures), rowStyle);
        }

        workbook.write(outputStream);
        workbook.close();
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
