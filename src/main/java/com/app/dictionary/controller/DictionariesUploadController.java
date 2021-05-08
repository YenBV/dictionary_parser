package com.app.dictionary.controller;

import com.app.dictionary.model.Dictionary;
import com.app.dictionary.service.parser.DictionaryParser;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class DictionariesUploadController {

    private final DictionaryParser parser;

    public DictionariesUploadController(DictionaryParser parser) {
        this.parser = parser;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<Dictionary> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        try(PDDocument doc = PDDocument.load(file.getInputStream())) {
            String text = new PDFTextStripper().getText(doc);
            return parser.parse(text);
        }
    }
}
