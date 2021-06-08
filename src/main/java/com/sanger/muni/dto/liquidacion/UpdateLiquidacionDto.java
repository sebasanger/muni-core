package com.sanger.muni.dto.liquidacion;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateLiquidacionDto {

    @NotNull
    private Long id;

    @NotNull
    private Long tipoLiquidacionId;

    @NotNull
    private Set<NewConceptoDto> conceptos;

    private String area;
    private LocalDate fechaIngreso;
    private LocalDate periodo;
    private String seccion;
    private String numeroRecibo;
}
