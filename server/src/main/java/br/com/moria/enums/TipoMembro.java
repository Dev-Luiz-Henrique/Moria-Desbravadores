package br.com.moria.enums;

import java.util.Arrays;

public enum TipoMembro {
    DIRETOR_CLUBE,
    DIRETOR_ASSOCIADO,
    TESOUREIRO,
    CAPELAO,
    SECRETARIO,
    INSTRUTOR_CLASSES_PROGRESSIVAS,
    CONSELHEIRO_UNIDADE,
    AUXILIAR,
    DESBRAVADOR,
    RESPONSAVEL;

    public static final String[] VOLUNTARIOS = Arrays.stream(TipoMembro.values())
        .filter(tipo -> tipo != DESBRAVADOR && tipo != RESPONSAVEL).map(TipoMembro::name).toArray(String[]::new);
}