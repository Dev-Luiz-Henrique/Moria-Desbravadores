package br.com.moria.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
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
    @Column(name = "data", nullable = false)
    private LocalDateTime data = LocalDateTime.now();

    @NotNull
    @Column(name = "data_vencimento", nullable = false)
    private LocalDateTime dataVencimento;

    @NotNull
    @Column(name = "data_pagamento", nullable = false)
    private LocalDateTime dataPagamento;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 8, fraction = 2)
    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "pagamento_realizado", nullable = false)
    private boolean pagamentoRealizado = false;

    @NotBlank
    @Size(max = 20, message = "Forma de pagamento deve ter no máximo 20 caracteres.")
    @Column(name = "forma_pagamento", nullable = false, length = 20)
    private String formaPagamento;

    @NotBlank
    @Size(max = 255, message = "Caminho do comprovante deve ter no máximo 255 caracteres.")
    @Column(name = "comprovante", nullable = false, length = 255)
    private String comprovante;

    @Size(max = 500, message = "Observações devem ter no máximo 500 caracteres.")
    @Column(name = "observacoes", length = 500)
    private String observacoes;

    @Column(name = "ativa", nullable = false)
    private boolean ativa = true;
}