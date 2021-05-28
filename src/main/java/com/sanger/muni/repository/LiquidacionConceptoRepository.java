package com.sanger.muni.repository;

import com.sanger.muni.model.LiquidacionConcepto;
import com.sanger.muni.model.LiquidacionConceptoKey;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LiquidacionConceptoRepository extends JpaRepository<LiquidacionConcepto, LiquidacionConceptoKey> {

}
