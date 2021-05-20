package com.sanger.muni.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
class LiqudacionConceptoKey implements Serializable {

    @Column(name = "liquidacion_id")
    Long liquidacionId;

    @Column(name = "concepto_id")
    Long conceptoId;

}