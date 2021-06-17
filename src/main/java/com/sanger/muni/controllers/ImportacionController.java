package com.sanger.muni.controllers;

import com.sanger.muni.services.ImportacionService;
import com.sanger.muni.utils.CSVHelper;

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
    public ResponseEntity<?> uploadLiquidaciones(@RequestParam("liquidaciones") MultipartFile liquidaciones,
            @RequestParam("liquidacionesConceptos") MultipartFile liquidacionesConceptos) {

        if (CSVHelper.hasCSVFormat(liquidaciones) && CSVHelper.hasCSVFormat(liquidacionesConceptos)) {
            try {
                importacionService.saveImportacion(liquidaciones, liquidacionesConceptos);

                return ResponseEntity.noContent().build();
            } catch (Exception e) {

                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.noContent().build();

    }

}