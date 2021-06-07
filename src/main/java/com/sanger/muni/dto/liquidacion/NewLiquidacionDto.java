package com.sanger.muni.dto.liquidacion;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewLiquidacionDto {

    private Long tipoLiquidacionId;
    private String area;
    private Set<NewConceptoDto> conceptos;
    private Long userId;
}
