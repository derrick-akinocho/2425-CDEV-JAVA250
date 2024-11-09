package com.example.demo.controller.export;

import com.example.demo.service.Clients.ExportCsvServiceClient;
import com.example.demo.service.Clients.ExportPdfServiceClient;
import com.example.demo.service.Clients.ExportXlsxServiceClient;
import com.example.demo.service.Factures.ExportPdfServiceFacture;
import com.example.demo.service.Factures.ExportXlsxServiceFacture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Controller pour réaliser l'export des articles.
 */
@Controller
@RequestMapping("export/factures")
public class ExportFactureController {

    @Autowired
    private ExportPdfServiceFacture exportPdfServiceFacture;
    @Autowired
    private ExportXlsxServiceFacture exportXlsxServiceFacture;

    /**
     * Export des Factures au format XLSX.
     */
    @GetMapping("xlsx")
    public void exportXLSX(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\"export-factures.xlsx\"");

        exportXlsxServiceFacture.exportData(response.getOutputStream());
    }

    /**
     * Export des Facture by Id au format PDF.
     */
    @GetMapping("{factureId}/pdf")
    public void exportPDF(HttpServletResponse response, @PathVariable("factureId") Long factureId){
        try {
            OutputStream outputStream = response.getOutputStream();

            exportPdfServiceFacture.exportPDF(outputStream, factureId);

            response.setHeader("Content-Disposition", "inline; filename=\"export-facture-" + factureId + ".pdf\"");
            response.setContentType("application/pdf");
            response.setContentLength(outputStream.toString().getBytes().length);

            outputStream.write(outputStream.toString().getBytes());
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Export des Factures au format PDF.
     */
    @GetMapping("pdf")
    public void exportPDF(HttpServletResponse response){
        try {
            OutputStream outputStream = response.getOutputStream();

            exportPdfServiceFacture.exportPDF(outputStream);

            response.setHeader("Content-Disposition", "inline; filename=\"export-factures.pdf\"");
            response.setContentType("application/pdf");
            response.setContentLength(outputStream.toString().getBytes().length);

            outputStream.write(outputStream.toString().getBytes());
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
