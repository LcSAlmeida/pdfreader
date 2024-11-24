package com.coy.pdfreader.controller;

import com.coy.pdfreader.service.PdfReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("v1/")
@RequiredArgsConstructor
public class PdfReaderController {

    private final PdfReaderService pdfReaderService;

    @PostMapping(value = "pdfreader", consumes = "multipart/form-data")
    public void pdfReader(@RequestParam MultipartFile file) throws IOException {
        pdfReaderService.readerPdf(file);
    }
}
