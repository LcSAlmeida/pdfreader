package com.coy.pdfreader.service;

import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PdfReaderService {

    private final LineProcessor lineProcessor;

    public void readerPdf(MultipartFile pdf) throws IOException {
        String currentDate = null;
        String operation = null;

        PDDocument document = PDDocument.load(pdf.getInputStream());

        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);

        String[] lines = text.split("\n");

        for (String line : lines) {
            line = line.trim();

            if (line.matches("\\d{2}/[A-Z]{3}/\\d{2}")) {
                currentDate = line;
            } else if (line.equalsIgnoreCase("PAGAMENTOS")) {
                operation = "PAGAMENTOS";
            } else if (line.equalsIgnoreCase("RECEBIMENTOS")) {
                operation = "RECEBIMENTOS";
            } else if (line.equalsIgnoreCase("NEGOCIAL")) {
                operation = "NEGOCIAL";
            } else if (line.equalsIgnoreCase("SERVICOS")) {
                operation = "SERVICOS";
            } else if (line.equalsIgnoreCase("ESTORNOS")) {
                operation = "ESTORNOS";
            } else if (line.startsWith("TOTAL")) {
                continue;
            } else {
                lineProcessor.execute(line, currentDate, operation);
            }
        }
    }
}
