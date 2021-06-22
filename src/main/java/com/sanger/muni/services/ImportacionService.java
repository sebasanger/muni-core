package com.sanger.muni.services;

import java.io.IOException;
import java.util.Set;

import javax.transaction.Transactional;

import com.sanger.muni.model.Liquidacion;
import com.sanger.muni.model.LiquidacionConcepto;
import com.sanger.muni.repository.LiquidacionConceptoRepository;
import com.sanger.muni.repository.LiquidacionRepository;
import com.sanger.muni.utils.CSVHelper;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ImportacionService {

    private final LiquidacionConceptoRepository liquidacionConceptoRepository;

    private final LiquidacionRepository liquidacionRepository;

    public void saveImportacion(MultipartFile liquidacionesFile, MultipartFile liquidacionesConceptosFile) {
        try {
            Set<Liquidacion> liquidaciones = CSVHelper.csvToLiquidaciones(liquidacionesFile.getInputStream());

            Set<LiquidacionConcepto> liquidacionConceptos = CSVHelper
                    .csvToLiquidacionConcepto(liquidacionesConceptosFile.getInputStream());

            liquidacionRepository.saveAll(liquidaciones);

            // liquidacionConceptoRepository.saveAll(liquidacionConceptos);

        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

}
