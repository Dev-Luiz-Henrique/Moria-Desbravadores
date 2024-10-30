const PaymentCategories = Object.freeze({
    ALIMENTACAO: 'ALIMENTACAO',
    VESTUARIO: 'VESTUARIO',
    TRANSPORTE: 'TRANSPORTE',
    LOCACAO_ESTRUTURAS: 'LOCACAO_ESTRUTURAS',
    LOCACAO_ESPACO: 'LOCACAO_ESPACO',
});

const getPaymentCategories = () => {
    return Object.values(PaymentCategories).map(payment => ({
        value: payment,
        label: payment.charAt(0) + payment.slice(1).toLowerCase()  
    }));
};

const getPaymentCategory = (value) => {
    return Object.keys(PaymentCategories).find(key => PaymentCategories[key] === value);
};

const getPayCategories = () => {
    return [
        PaymentCategories.ALIMENTACAO, 
        PaymentCategories.VESTUARIO,
        PaymentCategories.TRANSPORTE,
        PaymentCategories.LOCACAO_ESTRUTURAS,
        PaymentCategories.LOCACAO_ESPACO
    ];
};

export {
    PaymentCategories,
    getPaymentCategories,
    getPaymentCategory,
    getPayCategories    
};