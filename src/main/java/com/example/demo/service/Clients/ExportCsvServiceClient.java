package com.example.demo.service.Clients;

import com.example.demo.dto.ClientDto;
import com.example.demo.service.ClientService;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

@Service
public class ExportCsvServiceClient {

    @Autowired
    private ClientService clientService;

    public void exportBase(PrintWriter writer) {

        Iterable<ClientDto> clientDtos = clientService.findAll();

        writer.println("Nom ; Prenom ; Age");

        for (ClientDto clientDto : clientDtos) {

            writer.println("\"" + clientDto.getNom() + "\"" +
                    ";" + "\"" + clientDto.getPrenom() + "\"" +
                    ";" + "\"" + clientDto.getAge() + "\""
            );
        }

        writer.close();
    }

}
