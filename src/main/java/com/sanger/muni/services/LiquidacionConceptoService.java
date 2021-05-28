package com.sanger.muni.services;

import com.sanger.muni.model.LiquidacionConcepto;
import com.sanger.muni.model.LiquidacionConceptoKey;
import com.sanger.muni.repository.LiquidacionConceptoRepository;
import com.sanger.muni.services.base.BaseService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LiquidacionConceptoService
                extends BaseService<LiquidacionConcepto, LiquidacionConceptoKey, LiquidacionConceptoRepository> {

}
