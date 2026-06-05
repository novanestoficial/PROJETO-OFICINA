package com.novanest.projetooficina.entity;

import com.novanest.projetooficina.enums.StatusOS;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "ordens_servico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String numeroOs;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOS status;

    @Column(nullable = false)
    private LocalDate dataAbertura;

    private LocalDate dataFechamento;

    @Column(length = 500)
    private String descricaoProblema;

    private BigDecimal valorMaoDeObra;
    private BigDecimal valorPecas;
    private BigDecimal desconto;
    private BigDecimal valorTotal;

    @PrePersist
    public void prePersist() {
        this.dataAbertura = LocalDate.now();
    }
}
