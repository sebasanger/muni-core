package com.sanger.muni.services;

import com.sanger.muni.error.exceptions.EntityNotFoundException;
import com.sanger.muni.model.Concepto;
import com.sanger.muni.model.TipoConcepto;
import com.sanger.muni.repository.ConceptoRepository;
import com.sanger.muni.services.base.BaseService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConceptoService extends BaseService<Concepto, Long, ConceptoRepository> {

    private final TipoConceptoService tipoConceptoService;

    public Concepto saveConcepto(Concepto concepto) {

        Long tipoConceptoId = concepto.getTipoConcepto().getId();
        TipoConcepto tipoConcepto = tipoConceptoService.findById(tipoConceptoId)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de concepto no encontrado"));

        concepto.setTipoConcepto(tipoConcepto);

        return this.repository.save(concepto);
    }
}
