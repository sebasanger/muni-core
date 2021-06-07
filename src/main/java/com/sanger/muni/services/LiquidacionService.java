package com.sanger.muni.services;

import com.sanger.muni.dto.liquidacion.NewLiquidacionDto;
import com.sanger.muni.error.exceptions.EntityNotFoundException;
import com.sanger.muni.model.Concepto;
import com.sanger.muni.model.Liquidacion;
import com.sanger.muni.model.LiquidacionConcepto;
import com.sanger.muni.model.TipoLiquidacion;
import com.sanger.muni.model.UserEntity;
import com.sanger.muni.repository.LiquidacionRepository;
import com.sanger.muni.services.base.BaseService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LiquidacionService extends BaseService<Liquidacion, Long, LiquidacionRepository> {
    private final TipoLiquidacionService tipoLiquidacionService;
    private final UserEntityService userEntityService;
    private final ConceptoService conceptoService;

    public Liquidacion saveLiquidacion(NewLiquidacionDto newLiquidacionDto) {

        Liquidacion liquidacion = new Liquidacion();

        // Set user by id
        UserEntity user = userEntityService.findById(newLiquidacionDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        liquidacion.setUser(user);

        // Set Tipo Liquidacion by id
        TipoLiquidacion tipoLiquidacion = tipoLiquidacionService.findById(newLiquidacionDto.getTipoLiquidacionId())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de liquidacion no encontrado"));

        liquidacion.setTipoLiquidacion(tipoLiquidacion);

        newLiquidacionDto.getConceptos().forEach(concepto -> {

            Concepto conceptoToAdd = conceptoService.findById(concepto.getId()).get();

            LiquidacionConcepto liquidacionConcepto = new LiquidacionConcepto();
            liquidacionConcepto.setConcepto(conceptoToAdd);
            liquidacionConcepto.setCantidad(concepto.getCantidad());
            liquidacionConcepto.setImporte(concepto.getImporte());

            liquidacion.addConcepto(liquidacionConcepto);

        });

        return save(liquidacion);

    }

    public void deleteConcepto(Long id) {

        this.repository.deleteById(id);

    }

}
