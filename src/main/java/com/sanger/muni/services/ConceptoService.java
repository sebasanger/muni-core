package com.sanger.muni.services;

import com.sanger.muni.model.Concepto;
import com.sanger.muni.repository.ConceptoRepository;
import com.sanger.muni.services.base.BaseService;

import org.springframework.stereotype.Service;

@Service
public class ConceptoService extends BaseService<Concepto, Long, ConceptoRepository> {

}
