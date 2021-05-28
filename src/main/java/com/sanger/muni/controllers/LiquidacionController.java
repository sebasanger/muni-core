package com.sanger.muni.controllers;

import javax.validation.Valid;

import com.sanger.muni.dto.liquidacion.AddConceptoDto;
import com.sanger.muni.dto.liquidacion.DeleteConceptoDto;
import com.sanger.muni.model.Liquidacion;
import com.sanger.muni.services.LiquidacionService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/liquidacion")
@RequiredArgsConstructor
public class LiquidacionController extends BaseController<Liquidacion, Long, LiquidacionService> {

    private final LiquidacionService liquidacionService;

    @PostMapping("/")
    public ResponseEntity<Liquidacion> create(@Valid @RequestBody Liquidacion newLiquidacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(liquidacionService.saveLiquidacion(newLiquidacion));
    }

    @PutMapping("/add-concepto")
    public ResponseEntity<?> addConcepto(@Valid @RequestBody AddConceptoDto addConceptoDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(liquidacionService.addConcepto(addConceptoDto));
    }

    @DeleteMapping("/delete-concepto")
    public ResponseEntity<?> deleteConcepto(@Valid @RequestBody DeleteConceptoDto deleteConceptoDto) {
        liquidacionService.deleteConcepto(deleteConceptoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

}
