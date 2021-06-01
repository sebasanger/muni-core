package com.sanger.muni.services;

import com.sanger.muni.dto.liquidacion.AddConceptoDto;
import com.sanger.muni.dto.liquidacion.DeleteConceptoDto;
import com.sanger.muni.error.exceptions.EntityNotFoundException;
import com.sanger.muni.model.Concepto;
import com.sanger.muni.model.Liquidacion;
import com.sanger.muni.model.LiquidacionConcepto;
import com.sanger.muni.model.LiquidacionConceptoKey;
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
    private final LiquidacionConceptoService liquidacionConceptosService;

    public Liquidacion saveLiquidacion(Liquidacion liquidacion) {

        // Set user by id
        Long userId = liquidacion.getUser().getId();
        UserEntity user = userEntityService.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        liquidacion.setUser(user);

        // Set Tipo Liquidacion by id
        Long tipoLiquidacionId = liquidacion.getTipoLiquidacion().getId();
        TipoLiquidacion tipoLiquidacion = tipoLiquidacionService.findById(tipoLiquidacionId)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de liquidacion no encontrado"));

        liquidacion.setTipoLiquidacion(tipoLiquidacion);

        Liquidacion liquidacionCreada = save(liquidacion);

        liquidacion.getLiquidacionConceptos().forEach(a -> {

            Concepto concepto = conceptoService.findById(a.getConcepto().getId()).get();

            LiquidacionConcepto liquidacionConcepto = new LiquidacionConcepto();
            LiquidacionConceptoKey liquidacionConceptoKey = new LiquidacionConceptoKey();
            liquidacionConceptoKey.setConceptoId(concepto.getId());
            liquidacionConceptoKey.setLiquidacionId(liquidacionCreada.getId());
            liquidacionConcepto.setId(liquidacionConceptoKey);

            liquidacionConcepto.setConcepto(concepto);
            liquidacionConcepto.setLiquidacion(liquidacionCreada);
            liquidacionConcepto.setCantidad(a.getCantidad());
            liquidacionConcepto.setImporte(a.getImporte());

            liquidacionConceptosService.save(liquidacionConcepto);
        });
        return save(liquidacionCreada);

    }

    public LiquidacionConcepto addConcepto(AddConceptoDto addConceptoDto) {
        Concepto concepto = conceptoService.findById(addConceptoDto.getConceptoId()).get();
        Liquidacion liquidacion = this.repository.findById(addConceptoDto.getLiquidacionId()).get();

        LiquidacionConcepto liquidacionConcepto = new LiquidacionConcepto();
        LiquidacionConceptoKey liquidacionConceptoKey = new LiquidacionConceptoKey();
        liquidacionConceptoKey.setConceptoId(concepto.getId());
        liquidacionConceptoKey.setLiquidacionId(liquidacion.getId());
        liquidacionConcepto.setId(liquidacionConceptoKey);

        liquidacionConcepto.setConcepto(concepto);
        liquidacionConcepto.setLiquidacion(liquidacion);
        liquidacionConcepto.setCantidad(addConceptoDto.getCantidad());
        liquidacionConcepto.setImporte(addConceptoDto.getImporte());

        return liquidacionConceptosService.save(liquidacionConcepto);

    }

    public void deleteConcepto(DeleteConceptoDto deleteConceptoDto) {

        LiquidacionConceptoKey liquidacionConceptoKey = new LiquidacionConceptoKey();
        liquidacionConceptoKey.setConceptoId(deleteConceptoDto.getConceptoId());
        liquidacionConceptoKey.setLiquidacionId(deleteConceptoDto.getLiquidacionId());

        liquidacionConceptosService.deleteById(liquidacionConceptoKey);

    }
}
