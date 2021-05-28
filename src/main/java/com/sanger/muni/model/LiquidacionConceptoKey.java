package com.sanger.muni.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class LiquidacionConceptoKey implements Serializable {

    @Column(name = "liquidacion_id")
    private Long liquidacionId;

    @Column(name = "concepto_id")
    private Long conceptoId;

}