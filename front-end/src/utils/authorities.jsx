const Authorities = Object.freeze({
    DIRETOR_CLUBE: "DIRETOR_CLUBE",
    DIRETOR_ASSOCIADO: "DIRETOR_ASSOCIADO",
    TESOUREIRO: "TESOUREIRO",
    CAPELAO: "CAPELAO",
    SECRETARIO: "SECRETARIO",
    INSTRUTOR_CLASSES_PROGRESSIVAS: "INSTRUTOR_CLASSES_PROGRESSIVAS",
    CONSELHEIRO_UNIDADE: "CONSELHEIRO_UNIDADE",
    AUXILIAR: "AUXILIAR",
    DESBRAVADOR: "DESBRAVADOR",
    RESPONSAVEL: "RESPONSAVEL"
});

const getAuthorities = () => {
    return Object.values(Authorities);
};

const getAuthority = (value) => {
    return Object.keys(Authorities).find(key => Authorities[key] === value);
};

const getVoluntarios = () => {
    return [
        Authorities.DIRETOR_CLUBE, 
        Authorities.DIRETOR_ASSOCIADO,
        Authorities.TESOUREIRO,
        Authorities.CAPELAO,
        Authorities.SECRETARIO,
        Authorities.INSTRUTOR_CLASSES_PROGRESSIVAS,
        Authorities.CONSELHEIRO_UNIDADE,
        Authorities.AUXILIAR
    ];
};

export {
    Authorities,
    getAuthorities,
    getAuthority,
    getVoluntarios
};