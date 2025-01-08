package br.com.moria.domains.membro.enums;

import java.util.Arrays;

public enum MembroFuncao {
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

    public static final String[] VOLUNTARIOS = Arrays.stream(MembroFuncao.values())
            .filter(funcao -> funcao != DESBRAVADOR && funcao != RESPONSAVEL)
            .map(MembroFuncao::name).toArray(String[]::new);
}
