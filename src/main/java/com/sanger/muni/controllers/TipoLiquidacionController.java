package com.sanger.muni.controllers;

import com.sanger.muni.model.TipoLiquidacion;
import com.sanger.muni.services.TipoLiquidacionService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tipo-liquidacion")
@RequiredArgsConstructor
public class TipoLiquidacionController extends BaseController<TipoLiquidacion, Long, TipoLiquidacionService> {

}
