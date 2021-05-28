package com.sanger.muni.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LiquidacionConcepto {

    @EmbeddedId
    LiquidacionConceptoKey id;

    @ManyToOne
    @MapsId("liquidacionId")
    @JoinColumn(name = "liquidacion_id")
    private Liquidacion liquidacion;

    @ManyToOne
    @MapsId("conceptoId")
    @JoinColumn(name = "concepto_id")
    private Concepto concepto;

    private int cantidad;

    public LiquidacionConcepto(Liquidacion liquidacion, Concepto concepto, int cantidad) {
        this.id = new LiquidacionConceptoKey(liquidacion.getId(), concepto.getId());
        this.liquidacion = liquidacion;
        this.concepto = concepto;
        this.cantidad = cantidad;
    }

}
