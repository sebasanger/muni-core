package com.sanger.muni.services;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.sanger.muni.model.Concepto;
import com.sanger.muni.model.Liquidacion;
import com.sanger.muni.model.LiquidacionConcepto;
import com.sanger.muni.repository.ConceptoRepository;
import com.sanger.muni.repository.LiquidacionConceptoRepository;
import com.sanger.muni.repository.LiquidacionRepository;
import com.sanger.muni.utils.CSVHelper;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImportacionService {

    private final LiquidacionRepository liquidacionRepository;
    private final ConceptoRepository conceptoRepository;
    private final LiquidacionConceptoRepository liquidacionConceptoRepository;

    public void saveImportacion(MultipartFile liquidacionesFile, MultipartFile liquidacionesConceptosFile) {
        try {
            List<Liquidacion> liquidaciones = CSVHelper.csvToLiquidaciones(liquidacionesFile.getInputStream());

            Set<LiquidacionConcepto> liquidacionConceptos = CSVHelper
                    .csvToLiquidacionConcepto(liquidacionesConceptosFile.getInputStream());

            Set<Concepto> conceptos = CSVHelper.csvToConceptos(liquidacionesConceptosFile.getInputStream());

            conceptoRepository.saveAll(conceptos);
            // liquidacionRepository.saveAll(liquidaciones);
            liquidacionConceptoRepository.saveAll(liquidacionConceptos);

        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

}
