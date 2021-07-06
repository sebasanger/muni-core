package com.sanger.muni.services;

import java.io.IOException;
import java.util.Set;

import javax.transaction.Transactional;

import com.sanger.muni.model.Concepto;
import com.sanger.muni.model.Liquidacion;
import com.sanger.muni.model.LiquidacionConcepto;
import com.sanger.muni.repository.ConceptoRepository;
import com.sanger.muni.repository.LiquidacionConceptoRepository;
import com.sanger.muni.repository.LiquidacionRepository;
import com.sanger.muni.repository.UserEntityRepository;
import com.sanger.muni.utils.CSVHelper;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ImportacionService {

    private final UserEntityRepository userEntityRepository;

    private final LiquidacionRepository liquidacionRepository;

    private final LiquidacionConceptoRepository liquidacionConceptoRepository;

    private final ConceptoRepository conceptoRepository;

    public void saveImportacion(MultipartFile conceptos, MultipartFile maestros) {
        try {
            Set<Liquidacion> liquidaciones = CSVHelper.csvToLiquidaciones(conceptos.getInputStream(),
                    maestros.getInputStream());

            liquidaciones.forEach(liquidacion -> {

                Set<LiquidacionConcepto> liquidacionConceptos = liquidacion.getLiquidacionConceptos();
                liquidacionConceptos.forEach(liquidacionConcepto -> {
                    conceptoRepository.save(liquidacionConcepto.getConcepto());

                    Concepto concepto = conceptoRepository.findById(liquidacionConcepto.getConcepto().getId()).get();

                    liquidacionConcepto.setConcepto(concepto);

                    liquidacionConceptoRepository.save(liquidacionConcepto);

                });
                userEntityRepository.save(liquidacion.getUser());
                liquidacionRepository.save(liquidacion);

            });

        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

}
