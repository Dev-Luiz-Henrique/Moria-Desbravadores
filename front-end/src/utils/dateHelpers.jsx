export function formatDate(dateString) {
    const date = new Date(dateString);
    const options = { day: '2-digit', month: '2-digit', year: 'numeric' };
    return date.toLocaleDateString('pt-BR', options);
}

export function formatDateWithHours(dateString) {
    const date = new Date(dateString);
    const dia = String(date.getDate()).padStart(2, '0');
    const mes = String(date.getMonth() + 1).padStart(2, '0'); // Meses começam em 0
    const ano = date.getFullYear();
    const horas = String(date.getHours()).padStart(2, '0');
    return `${dia}/${mes}/${ano} - ${horas}hrs`;
};