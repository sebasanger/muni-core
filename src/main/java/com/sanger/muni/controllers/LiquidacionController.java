package com.sanger.muni.controllers;

import javax.validation.Valid;

import com.sanger.muni.model.Liquidacion;
import com.sanger.muni.services.LiquidacionService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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

}
