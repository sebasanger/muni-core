package com.sanger.muni.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LiquidacionConcepto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "liquidacion_id")
    private Liquidacion liquidacion;

    @ManyToOne
    @JoinColumn(name = "concepto_id")
    private Concepto concepto;

    private Short cantidad;

    private Double importe;

    public Double getSubtotal() {
        return this.cantidad * this.getImporte();
    }

}
