const Payments = Object.freeze({
    DINHEIRO:   'DINHEIRO',
    PIX:        'PIX',
    CREDITO:    'CREDITO',
    DEBITO:     'DEBITO',
    BOLETO:     'BOLETO',
});

const getPayments = () => {
    return Object.values(Payments).map(payment => ({
        value: payment,
        label: payment.charAt(0) + payment.slice(1) 
    }));
};

const getPayment = (value) => {
    return Object.keys(Payments).find(key => Payments[key] === value);
};

const getPays = () => {
    return [
        Payments.DINHEIRO, 
        Payments.PIX,
        Payments.CREDITO,
        Payments.DEBITO,
        Payments.BOLETO
    ];
};

export {
    Payments,
    getPayments,
    getPayment,
    getPays    
};