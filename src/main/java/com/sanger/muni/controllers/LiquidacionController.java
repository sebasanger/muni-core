package com.sanger.muni.controllers;

import javax.validation.Valid;

import com.sanger.muni.dto.liquidacion.AddConceptoDto;
import com.sanger.muni.dto.liquidacion.NewLiquidacionDto;
import com.sanger.muni.dto.liquidacion.UpdateLiquidacionDto;
import com.sanger.muni.model.Liquidacion;
import com.sanger.muni.model.UserEntity;
import com.sanger.muni.services.LiquidacionService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<Liquidacion> create(@Valid @RequestBody NewLiquidacionDto newLiquidacion,
            @AuthenticationPrincipal UserEntity user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(liquidacionService.saveLiquidacion(newLiquidacion));
    }

    @PutMapping("/")
    public ResponseEntity<Liquidacion> update(@Valid @RequestBody UpdateLiquidacionDto updateLiquidacionDto,
            @AuthenticationPrincipal UserEntity user) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(liquidacionService.updateLiquidacion(updateLiquidacionDto));
    }

    @PutMapping("add-concepto")
    public ResponseEntity<Liquidacion> addCocnepto(@Valid @RequestBody AddConceptoDto addConceptoDto,
            @AuthenticationPrincipal UserEntity user) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(liquidacionService.addConceptoToLiquidacion(addConceptoDto));
    }

    @DeleteMapping("/remove-concepto/{id}")
    public ResponseEntity<Void> deleteConcepto(@PathVariable Long id) {
        liquidacionService.deleteConcepto(id);

        return ResponseEntity.noContent().build();
    }

}
