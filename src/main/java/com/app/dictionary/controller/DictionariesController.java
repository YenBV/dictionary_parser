package com.app.dictionary.controller;

import com.app.dictionary.model.Dictionary;
import com.app.dictionary.service.DictionaryParser;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class DictionariesController {

    private final DictionaryParser parser;

    public DictionariesController(DictionaryParser parser) {
        this.parser = parser;
    }

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Dictionary handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        PDDocument doc = PDDocument.load(file.getInputStream());
        String text = new PDFTextStripper().getText(doc);
        return parser.parse(text);
    }
}
