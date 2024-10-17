package br.com.moria.models;

import java.math.BigDecimal;

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
@Table(name = "recursos")
@Getter
@Setter
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_evento", referencedColumnName = "id")
    private Evento evento;

    @NotBlank
    @Size(max = 255, message = "Nome deve ter no máximo 255 caracteres.")
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotBlank
    @Size(max = 255, message = "Descrição deve ter no máximo 255 caracteres.")
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 8, fraction = 2)
    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @NotBlank
    @Size(max = 50, message = "Tipo de transação deve ter no máximo 50 caracteres.")
    @Column(name = "tipo_transacao", nullable = false, length = 50)
    private String tipoTransacao;

    @NotBlank
    @Size(max = 20, message = "Forma de pagamento deve ter no máximo 20 caracteres.")
    @Column(name = "forma_pagamento", nullable = false, length = 20)
    private String formaPagamento;

    @NotBlank
    @Size(max = 255, message = "Nota fiscal deve ter no máximo 255 caracteres.")
    @Column(name = "nota_fiscal", nullable = false)
    private String notaFiscal;

    @NotBlank
    @Size(max = 50, message = "Categoria deve ter no máximo 50 caracteres.")
    @Column(name = "categoria", nullable = false, length = 50)
    private String categoria;

    @NotBlank
    @Size(max = 20, message = "Status deve ter no máximo 20 caracteres.")
    @Column(name = "status", nullable = false, length = 20)
    private String status;
}