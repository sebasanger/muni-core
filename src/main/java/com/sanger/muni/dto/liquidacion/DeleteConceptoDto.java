package com.sanger.muni.dto.liquidacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteConceptoDto {

    private Long conceptoId;
    private Long liquidacionId;

}
