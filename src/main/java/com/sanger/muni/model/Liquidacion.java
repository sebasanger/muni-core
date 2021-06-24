package com.sanger.muni.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "liquidaciones")
public class Liquidacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private UserEntity user;

    private Long legajo;

    @ManyToOne
    @JoinColumn(name = "tipo_liquidacion_id", nullable = true)
    private TipoLiquidacion tipoLiquidacion;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    @JsonManagedReference
    @OneToMany(mappedBy = "liquidacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LiquidacionConcepto> liquidacionConceptos = new HashSet<>();

    private String area;

    private LocalDate fechaIngreso;

    private LocalDate periodo;

    private String seccion;

    private String numeroRecibo;

    @CreatedDate
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Double getTotalRemunerativo() {
        return this.liquidacionConceptos.stream().filter(liquidacionConcepto -> liquidacionConcepto.getConcepto()
                .getTipoConcepto().equals(TipoConcepto.REMUNERATIVO)).mapToDouble(LiquidacionConcepto::getSubtotal)
                .sum();
    }

    public Double getTotalNoRemunerativo() {
        return this.liquidacionConceptos.stream().filter(liquidacionConcepto -> liquidacionConcepto.getConcepto()
                .getTipoConcepto().equals(TipoConcepto.NO_REMUNERATIVO)).mapToDouble(LiquidacionConcepto::getSubtotal)
                .sum();
    }

    public Double getTotalDeducciones() {
        return this.liquidacionConceptos.stream().filter(liquidacionConcepto -> liquidacionConcepto.getConcepto()
                .getTipoConcepto().equals(TipoConcepto.DEDUCCION)).mapToDouble(LiquidacionConcepto::getSubtotal).sum();
    }

    public Double getTotal() {
        return (this.getTotalNoRemunerativo() + this.getTotalRemunerativo()) - this.getTotalDeducciones();
    }

    public void addLiquidacionConcepto(LiquidacionConcepto liquidacionConcepto) {
        this.liquidacionConceptos.add(liquidacionConcepto);
        liquidacionConcepto.setLiquidacion(this);
    }

    public void deleteLiquidacionConcepto(LiquidacionConcepto liquidacionConcepto) {
        this.liquidacionConceptos.remove(liquidacionConcepto);
        liquidacionConcepto.setLiquidacion(null);
    }

}
