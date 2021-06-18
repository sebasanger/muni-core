package com.sanger.muni.controllers;

import com.sanger.muni.services.ImportacionService;
import com.sanger.muni.utils.CSVHelper;

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
    public ResponseEntity<String> uploadLiquidaciones(@RequestParam("liquidaciones") MultipartFile liquidaciones,
            @RequestParam("liquidacionesConceptos") MultipartFile liquidacionesConceptos) {

        if (CSVHelper.hasCSVFormat(liquidaciones) && CSVHelper.hasCSVFormat(liquidacionesConceptos)) {
            try {
                importacionService.saveImportacion(liquidaciones, liquidacionesConceptos);

                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Greet");
            } catch (Exception e) {

                return ResponseEntity.status(HttpStatus.CONFLICT).body("Error");
            }
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");

    }

}