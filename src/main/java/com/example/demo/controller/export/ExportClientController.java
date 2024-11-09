package com.example.demo.controller.export;

import com.example.demo.service.Clients.ExportCsvServiceClient;
import com.example.demo.service.Clients.ExportPdfServiceClient;
import com.example.demo.service.Clients.ExportXlsxServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Controller pour réaliser l'export des articles.
 */
@Controller
@RequestMapping("export/clients")
public class ExportClientController {

    @Autowired
    private ExportCsvServiceClient exportCsvServiceClient;
    @Autowired
    private ExportPdfServiceClient exportPdfServiceClient;
    @Autowired
    private ExportXlsxServiceClient exportXlsxServiceClient;

    /**
     * Export des Clients au format CSV.
     */
    @GetMapping("csv")
    public void exportCSV(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"export-clients-avec-age.csv\"");

        exportCsvServiceClient.exportBase(response.getWriter());
    }

    /**
     * Export des Clients au format XLSX.
     */
    @GetMapping("xlsx")
    public void exportXLSX(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\"export-clients-avec-age.xlsx\"");

        exportXlsxServiceClient.exportData(response.getOutputStream());
    }

    /**
     * Export des Clients au format PDF.
     */
    @GetMapping("pdf")
    public void exportPDF(HttpServletResponse response){
        try {
            OutputStream outputStream = response.getOutputStream();

            exportPdfServiceClient.exportPDF(outputStream);

            response.setHeader("Content-Disposition", "inline; filename=\"export-clients-avec-age.pdf\"");
            response.setContentType("application/pdf");
            response.setContentLength(outputStream.toString().getBytes().length);

            outputStream.write(outputStream.toString().getBytes());
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
