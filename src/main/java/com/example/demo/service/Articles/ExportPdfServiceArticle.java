package com.example.demo.service.Articles;

import com.example.demo.dto.ArticleDto;
import com.example.demo.service.ArticleService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

@Service
public class ExportPdfServiceArticle {

    @Autowired
    private ArticleService articleService;

    public void exportPDF(OutputStream outputStream) throws DocumentException {

        Iterable<ArticleDto> articleDtos = articleService.findAll();

        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        document.open();

        com.itextpdf.text.Font boldFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, com.itextpdf.text.Font.BOLD);

        for (ArticleDto articleDto : articleDtos) {

            Image image = getImageFromUrl(articleDto.getImageUrl());
            if (image != null) {
                image.scaleToFit(100, 100); // Redimensionner l'image si nécessaire
                document.add(image);
            }

            document.add(new Paragraph("Articles : " + articleDto.getLibelle(), boldFont));
            document.add(Chunk.NEWLINE);
            // Le tableau
            PdfPTable table = new PdfPTable(2);

            // Ajouter la première ligne
            table.addCell("Libelle");
            table.addCell(articleDto.getLibelle());
            table.addCell("Prix");
            table.addCell(String.valueOf(articleDto.getPrix()));
            table.addCell("Quantite");
            table.addCell(String.valueOf(articleDto.getQuantite()));

            document.add(table);
        }

        document.close();

    }

    private Image getImageFromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            InputStream inputStream = url.openStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer, 0, 1024)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            byte[] imageBytes = outputStream.toByteArray();
            return Image.getInstance(imageBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
