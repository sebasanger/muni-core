package com.sanger.muni.dto.liquidacion;

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
public class AddConceptoDto {

    @NotNull
    private Set<NewConceptoDto> conceptos;

    @NotNull
    private Long liquidacionId;

}
