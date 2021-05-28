package com.sanger.muni.controllers;

import javax.validation.Valid;

import com.sanger.muni.model.Concepto;
import com.sanger.muni.services.ConceptoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/concepto")
@RequiredArgsConstructor
public class ConceptoController extends BaseController<Concepto, Long, ConceptoService> {
    private final ConceptoService conceptoService;

    @PostMapping("/")
    public ResponseEntity<Concepto> create(@Valid @RequestBody Concepto newConcepto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(conceptoService.saveConcepto(newConcepto));
    }
}
