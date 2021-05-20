package com.sanger.muni.controllers;

import com.sanger.muni.model.Concepto;
import com.sanger.muni.services.ConceptoService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/concepto")
@RequiredArgsConstructor
public class ConceptoController extends BaseController<Concepto, Long, ConceptoService> {

}
