package com.sanger.muni.dto.liquidacion;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.sanger.muni.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetLiquidacionesDto {

    private Long id;
    private UserEntity user;
    private String tipoLiquidacion;
    private String area;
    private LocalDate fechaIngreso;
    private LocalDate periodo;
    private String seccion;
    private Long numeroRecibo;
    private Double totalRemuneracionConAportes;
    private Double totalRemuneracionSinAportes;
    private Double deducciones;
    private Double totalCobrar;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Long conceptoId;
    private String conceptoDescripcion;
    private String tipoConcepto;
    private Short cantidad;
    private Double importe;

}
