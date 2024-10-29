export function abbreviateName(name) {
    const ignoreWords = ["dos", "da", "de", "do", "e"];
    const parts = name.trim().split(" ").filter(part => !ignoreWords.includes(part.toLowerCase()));
    
    if (parts.length <= 3) 
        return name; 

    const firstName = parts[0];
    const lastName = parts[parts.length - 1];
    const middleInitials = parts.slice(1, parts.length - 1).map(part => part.charAt(0) + '.').join(' ');
    return `${firstName} ${middleInitials} ${lastName}`;
}

export function normalizeUnderscore(role) {
    return role.toLowerCase().split('_')
        .map(word => word.charAt(0).toUpperCase() + word.slice(1)).join(' ');
}

// Formatação de dados do membro

export function formatEndereco(endereco, logradouro, numero) {
    const { bairro, cidade, estado } = endereco;
    return `${logradouro}, ${numero} - ${bairro}, ${cidade} - ${estado}`;
}

export function formatPhoneNumber(number) {
    const cleaned = number.replace(/\D/g, "");
    return cleaned.replace(/(\d{2})(\d{5})(\d{4})/, "($1) $2-$3");
};

export function cleanPhoneNumber (number) {
    number.replace(/\D/g, "");
} 

export const memberUtils = {
    formatEndereco,
    formatPhoneNumber,
    cleanPhoneNumber
};