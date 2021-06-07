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
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "tipo_liquidacion_id", nullable = false)
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

    private Double totalRemuneracionConAportes;

    private Double totalRemuneracionSinAportes;

    private Double deducciones;

    private Double totalCobrar;

    @CreatedDate
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Double getTotal() {
        return this.liquidacionConceptos.stream().mapToDouble(LiquidacionConcepto::getSubtotal).sum();
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
