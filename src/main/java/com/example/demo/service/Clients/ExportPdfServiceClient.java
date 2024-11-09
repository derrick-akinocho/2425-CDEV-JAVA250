package com.example.demo.service.Clients;

import com.example.demo.dto.ClientDto;
import com.example.demo.service.ClientService;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;

@Service
public class ExportPdfServiceClient {

    @Autowired
    private ClientService clientService;

    public void exportPDF(OutputStream outputStream) throws DocumentException {

        Iterable<ClientDto> clientDtos = clientService.findAll();

        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        document.open();

        com.itextpdf.text.Font boldFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, com.itextpdf.text.Font.BOLD);

        for (ClientDto clientDto : clientDtos) {

            document.add(new Paragraph("Clients : " + clientDto.getId(), boldFont));
            document.add(Chunk.NEWLINE);
            // Le tableau
            PdfPTable table = new PdfPTable(2);

            // Ajouter la première ligne
            table.addCell("Nom");
            table.addCell(clientDto.getNom());
            table.addCell("Prenom");
            table.addCell(String.valueOf(clientDto.getPrenom()));
            table.addCell("Age");
            table.addCell(String.valueOf(clientDto.getAge()));

            document.add(table);
        }

        document.close();

    }

}
