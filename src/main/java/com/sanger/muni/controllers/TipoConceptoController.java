package com.sanger.muni.controllers;

import com.sanger.muni.model.TipoConcepto;
import com.sanger.muni.services.TipoConceptoService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tipoconcepto")
@RequiredArgsConstructor
public class TipoConceptoController extends BaseController<TipoConcepto, Long, TipoConceptoService> {

}
