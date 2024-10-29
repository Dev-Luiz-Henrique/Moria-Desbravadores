package br.com.moria.models;

import java.time.LocalDateTime;

import br.com.moria.enums.FormaPagamento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mensalidades")
@Getter
@Setter
public class Mensalidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_membro", referencedColumnName = "id")
    private Membro membro;

    @NotNull
    @Column(name = "data", nullable = false, columnDefinition= "DATETIME")
    private LocalDateTime data;

    @NotNull
    @Column(name = "data_vencimento", nullable = false, columnDefinition= "DATETIME")
    private LocalDateTime dataVencimento;

    @Column(name = "data_pagamento", columnDefinition= "DATETIME")
    private LocalDateTime dataPagamento;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "valor", nullable = false, columnDefinition = "DOUBLE(8,2)")
    private Double valor;

    @Column(name = "pagamento_realizado", nullable = false)
    private boolean pagamentoRealizado;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento", length = 20)
    private FormaPagamento formaPagamento;

    @Size(max = 255, message = "Caminho do comprovante deve ter no máximo 255 caracteres.")
    @Column(name = "comprovante", length = 255)
    private String comprovante;

    @Size(max = 500, message = "Observações devem ter no máximo 500 caracteres.")
    @Column(name = "observacoes", length = 500)
    private String observacoes;
}