package com.sanger.muni.controllers;

import com.sanger.muni.model.LiquidacionConcepto;
import com.sanger.muni.model.LiquidacionConceptoKey;
import com.sanger.muni.services.LiquidacionConceptoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/liquidacion-concepto")
@RequiredArgsConstructor
public class LiquidacionConceptoController
        extends BaseController<LiquidacionConcepto, LiquidacionConceptoKey, LiquidacionConceptoService> {

}
