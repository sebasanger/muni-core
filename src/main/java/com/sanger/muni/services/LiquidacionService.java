package com.sanger.muni.services;

import com.sanger.muni.dto.liquidacion.AddConceptoDto;
import com.sanger.muni.dto.liquidacion.NewLiquidacionDto;
import com.sanger.muni.dto.liquidacion.UpdateLiquidacionDto;
import com.sanger.muni.error.exceptions.EntityNotFoundException;
import com.sanger.muni.model.Concepto;
import com.sanger.muni.model.Liquidacion;
import com.sanger.muni.model.LiquidacionConcepto;
import com.sanger.muni.model.TipoLiquidacion;
import com.sanger.muni.model.UserEntity;
import com.sanger.muni.repository.LiquidacionConceptoRepository;
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
    private final LiquidacionConceptoRepository liquidacionConceptoRepository;

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

            liquidacion.addLiquidacionConcepto(liquidacionConcepto);

        });

        liquidacion.setArea(newLiquidacionDto.getArea());
        liquidacion.setFechaIngreso(newLiquidacionDto.getFechaIngreso());
        liquidacion.setPeriodo(newLiquidacionDto.getPeriodo());
        liquidacion.setSeccion(newLiquidacionDto.getSeccion());
        liquidacion.setNumeroRecibo(newLiquidacionDto.getNumeroRecibo());

        return save(liquidacion);

    }

    public Liquidacion updateLiquidacion(UpdateLiquidacionDto updateLiquidacionDto) {

        Liquidacion liquidacion = this.repository.findById(updateLiquidacionDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Liquidacion no encotrada"));

        // Set Tipo Liquidacion by id
        TipoLiquidacion tipoLiquidacion = tipoLiquidacionService.findById(updateLiquidacionDto.getTipoLiquidacionId())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de liquidacion no encontrado"));

        liquidacion.setTipoLiquidacion(tipoLiquidacion);

        updateLiquidacionDto.getConceptos().forEach(concepto -> {

            Concepto conceptoToAdd = conceptoService.findById(concepto.getId()).get();

            LiquidacionConcepto liquidacionConcepto = new LiquidacionConcepto();
            liquidacionConcepto.setConcepto(conceptoToAdd);
            liquidacionConcepto.setCantidad(concepto.getCantidad());
            liquidacionConcepto.setImporte(concepto.getImporte());

            liquidacion.addLiquidacionConcepto(liquidacionConcepto);

        });

        liquidacion.setArea(updateLiquidacionDto.getArea());
        liquidacion.setFechaIngreso(updateLiquidacionDto.getFechaIngreso());
        liquidacion.setPeriodo(updateLiquidacionDto.getPeriodo());
        liquidacion.setSeccion(updateLiquidacionDto.getSeccion());
        liquidacion.setNumeroRecibo(updateLiquidacionDto.getNumeroRecibo());

        return save(liquidacion);

    }

    public Liquidacion addConceptoToLiquidacion(AddConceptoDto addConceptoDto) {

        Liquidacion liquidacion = this.repository.findById(addConceptoDto.getLiquidacionId())
                .orElseThrow(() -> new EntityNotFoundException("Liquidacion no encontrada"));

        addConceptoDto.getConceptos().forEach(concepto -> {

            Concepto conceptoToAdd = conceptoService.findById(concepto.getId()).get();

            LiquidacionConcepto liquidacionConcepto = new LiquidacionConcepto();
            liquidacionConcepto.setConcepto(conceptoToAdd);
            liquidacionConcepto.setCantidad(concepto.getCantidad());
            liquidacionConcepto.setImporte(concepto.getImporte());

            liquidacion.addLiquidacionConcepto(liquidacionConcepto);

        });

        return save(liquidacion);

    }

    public void deleteConcepto(Long id) {
        LiquidacionConcepto liquidacionConcepto = this.liquidacionConceptoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Liquidacion no encontrada"));

        Liquidacion liquidacion = liquidacionConcepto.getLiquidacion();
        liquidacion.deleteLiquidacionConcepto(liquidacionConcepto);

        this.repository.save(liquidacion);
    }

}
