package com.sanger.muni.controllers;

import com.sanger.muni.services.ImportacionService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/importacion")
@RequiredArgsConstructor
public class ImportacionController {

    private final ImportacionService importacionService;

    @PostMapping("/uploadLiquidaciones")
    public ResponseEntity<String> uploadLiquidaciones(@RequestParam("file") MultipartFile file) {

        // if (CSVHelper.hasCSVFormat(file)) {
        try {
            importacionService.saveImportacion(file);

            return ResponseEntity.status(HttpStatus.CREATED).body("Greet");
        } catch (Exception e) {
            System.err.println(e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.fillInStackTrace().getMessage());
        }
        // }

        // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal
        // server error");

    }

}