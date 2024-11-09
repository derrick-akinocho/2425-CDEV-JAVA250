package com.example.demo.service.Factures;

import com.example.demo.dto.FactureDto;
import com.example.demo.dto.LigneFactureDto;
import com.example.demo.service.FactureService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.time.format.DateTimeFormatter;

@Service
public class ExportPdfServiceFacture {

    @Autowired
    private FactureService factureService;

    public void exportPDF(OutputStream outputStream) throws DocumentException {

        Iterable<FactureDto> factureDtos = factureService.findAll();

        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        document.open();

        com.itextpdf.text.Font boldFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, com.itextpdf.text.Font.BOLD);

        Paragraph paragraph = new Paragraph("___________________Factures___________________", boldFont);
        paragraph.setAlignment(Element.ALIGN_CENTER);

        document.add(paragraph);

        for (FactureDto factureDto : factureDtos) {

            document.add(Chunk.NEWLINE);

            document.add(new Paragraph("- N°" + factureDto.getId() + " de " + factureDto.getClientNom() + " " + factureDto.getClientPrenom(), boldFont));
            document.add(Chunk.NEWLINE);
            // Le tableau
            PdfPTable table = new PdfPTable(3);

            // Ajouter la première ligne
            table.addCell("Désignation");
            table.addCell("Quantité");
            table.addCell("Prix Unitaire");

            for (LigneFactureDto ligneFactureDto : factureDto.getLigneFactures()) {
                table.addCell(ligneFactureDto.getArticle());
                table.addCell(ligneFactureDto.getQuantite().toString());
                table.addCell(ligneFactureDto.getPrixUnitaire().toString());
            }

            document.add(table);

        }

        document.close();
    }

    public void exportPDF(OutputStream outputStream, Long factureId) throws DocumentException {

        FactureDto factureDto = factureService.findById(factureId);

        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        document.open();

        com.itextpdf.text.Font boldFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, com.itextpdf.text.Font.BOLD);

        Paragraph paragraph = new Paragraph("___________________Factures___________________", boldFont);
        paragraph.setAlignment(Element.ALIGN_CENTER);

        document.add(paragraph);

        document.add(Chunk.NEWLINE);

        document.add(new Paragraph("- N°" + factureDto.getId() + " de " + factureDto.getClientNom() + " " + factureDto.getClientPrenom(), boldFont));
        document.add(Chunk.NEWLINE);
        // Le tableau
        PdfPTable table = new PdfPTable(3);

        // Ajouter la première ligne
        table.addCell("Désignation");
        table.addCell("Quantité");
        table.addCell("Prix Unitaire");

        for (LigneFactureDto ligneFactureDto : factureDto.getLigneFactures()) {
            table.addCell(ligneFactureDto.getArticle());
            table.addCell(ligneFactureDto.getQuantite().toString());
            table.addCell(ligneFactureDto.getPrixUnitaire().toString());
        }

        document.add(table);

        document.close();
    }
}
