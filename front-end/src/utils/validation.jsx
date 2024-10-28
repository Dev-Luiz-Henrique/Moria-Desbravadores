import { getAuthority } from "./authorities";

export const validate = {
    nome: (value) => {
        if (value.length < 3 || value.length > 100)
            return "Nome deve ter entre 3 e 100 caracteres.";
        return "";
    },

    sexo: (value) => {
        if (!["M", "F", "O"].includes(value.toUpperCase()))
            return "Sexo deve ser 'M', 'F' ou 'O'.";
        return "";
    },
    
    data: (value) => {
        const today = new Date();
        const birthDate = new Date(value);
        if (birthDate >= today)
            return "Data de nascimento deve ser uma data passada.";
        return "";
    },

    telefone: (value) => {
        if (!/^\d{10}$/.test(value))
            return "Telefone deve ter exatamente 10 dígitos.";
        return "";
    },

    celular: (value) => { console.log("cell " + value)
        if (!/^\d{11}$/.test(value))
            return "Celular deve ter exatamente 11 dígitos.";
        return "";
    },

    email: (value) => {
        if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value))
            return "O formato do email é inválido.";
        return "";
    },

    senha: (value) => {
        if (value.length < 8 || !/[A-Z]/.test(value) || !/[a-z]/.test(value) || !/[#@!$%&]/.test(value))
            return "Senha deve ter no mínimo 8 caracteres e incluir letras maiúsculas, minúsculas e caracteres especiais.";
        return "";
    },

    logradouro: (value) => {
        if (value.length > 255)
            return "Logradouro deve ter no máximo 255 caracteres.";
        return "";
    },
    
    numero: (value) => {
        if (value === null || value < 1)
            return "Número deve ser maior que 0.";
        return "";
    },
    
    cpf: (value) => {
        if (!/^\d{11}$/.test(value))
            return "CPF deve ter exatamente 11 dígitos.";
        return "";
    },
    
    rg: (value) => {
        if (value.length > 20)
            return "RG deve ter no máximo 20 caracteres.";
        return "";
    },
    
    orgaoExpedidor: (value) => {
        if (value.length > 15)
            return "Órgão Expedidor deve ter no máximo 15 caracteres.";
        return "";
    },
    
    profissionalSaude: (value) => {
        if (value.length > 50)
            return "Profissional de Saúde deve ter no máximo 50 caracteres.";
        return "";
    },
    
    tamanhoCamisa: (value) => {
        if (value.length > 5)
            return "Tamanho da camisa deve ter no máximo 5 caracteres.";
        return "";
    },
    
    estadoCivil: (value) => {
        if (!value)
            return "Estado Civil é obrigatório.";
        return "";
    },
    
    batizado: (value) => {
        if (value === null)
            return "Campo Batizado é obrigatório.";
        return "";
    },
        
    tipo: (value) => {
        if (!value)
            return "Função é obrigatório.";
        if (!getAuthority(value))
            return "Função inválido.";
        return "";
    },
};