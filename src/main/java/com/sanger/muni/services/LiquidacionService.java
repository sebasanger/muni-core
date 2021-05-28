package com.sanger.muni.services;

import com.sanger.muni.error.exceptions.EntityNotFoundException;
import com.sanger.muni.model.Liquidacion;
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

        /*
         * liquidacion.setDescripciones((liquidacion.getDescripciones().stream().map(a
         * -> { Concepto concepto =
         * conceptoService.findById(a.getConcepto().getId()).get(); Descripcion
         * descripcion = new Descripcion(); descripcion.setConcepto(concepto);
         * descripcion.setCantidad(a.getCantidad()); return descripcion;
         * }).collect(Collectors.toSet())));
         * 
         */
        return save(liquidacion);

    }
}
