package br.com.moria.models;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.moria.enums.FormaPagamento;
import br.com.moria.enums.RecursoEvento;
import br.com.moria.enums.StatusPagamento;
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

    @NotNull(message = "Evento não pode ser nulo.")
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_evento", referencedColumnName = "id")
    @JsonBackReference
    private Evento evento;

    @NotBlank(message = "Nome não pode ser vazio.")
    @Size(max = 255, message = "Nome deve ter no máximo 255 caracteres.")
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotBlank(message = "Descrição não pode ser vazia.")
    @Size(max = 255, message = "Descrição deve ter no máximo 255 caracteres.")
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @NotNull(message = "Valor não pode ser nulo.")
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 8, fraction = 2)
    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @NotNull(message = "Quantidade não pode ser nula.")
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "quantidade", nullable = false, columnDefinition = "DOUBLE(8,2)")
    private Double quantidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento", nullable = false, length = 20)
    private FormaPagamento formaPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false, length = 30)
    private RecursoEvento categoria;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pagamento", nullable = false, length = 20)
    private StatusPagamento statusPagamento;
}