package com.example.demo.controller.export;

import com.example.demo.dto.ArticleDto;
import com.example.demo.dto.ClientDto;
import com.example.demo.service.ArticleService;
import com.example.demo.service.Articles.ExportCsvServiceArticle;
import com.example.demo.service.Articles.ExportPdfServiceArticle;
import com.example.demo.service.Articles.ExportXlsxServiceArticle;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.List;

/**
 * Controller pour réaliser l'export des articles.
 */
@Controller
@RequestMapping("export/articles")
public class ExportArticleController {

    @Autowired
    private ExportCsvServiceArticle exportCsvServiceArticle;
    @Autowired
    private ExportXlsxServiceArticle exportXlsxServiceArticle;
    @Autowired
    private ExportPdfServiceArticle exportPdfServiceArticle;

    /**
     * Export des articles au format CSV.
     */
    @GetMapping("csv")
    public void exportCSV(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"export-articles.csv\"");

        exportCsvServiceArticle.exportBase(response.getWriter());
    }

    /**
     * Export des Articles au format XLSX.
     */
    @GetMapping("xlsx")
    public void exportXLSX(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\"export-articles.xlsx\"");

        exportXlsxServiceArticle.exportData(response.getOutputStream());
    }

    /**
     * Export des Articles au format PDF.
     */
    @GetMapping("pdf")
    public void exportPDF(HttpServletResponse response) {

        try {

            OutputStream outputStream = response.getOutputStream();

            exportPdfServiceArticle.exportPDF(response.getOutputStream());

            response.setHeader("Content-Disposition", "inline; filename=\"export-articles.pdf\"");
            response.setContentType("application/pdf");
            response.setContentLength(outputStream.toString().getBytes().length);

            outputStream.write(outputStream.toString().getBytes());
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
