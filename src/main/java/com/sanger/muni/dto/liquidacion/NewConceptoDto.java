package com.sanger.muni.dto.liquidacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewConceptoDto {

    private Long id;
    private Float cantidad;
    private Double importe;

}
