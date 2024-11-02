const PaymentStatuses = Object.freeze({
    PAGO: 'PAGO',
    PENDENTE: 'PENDENTE',
});

const getPaymentStatus = () => {
    return Object.values(PaymentStatuses).map(payment => ({
        value: payment,
        label: payment.charAt(0) + payment.slice(1)
    }));
};

const PaymentStatus = (value) => {
    return Object.keys(PaymentStatuses).find(key => PaymentStatuses[key] === value);
};

const getPaymentStats = (value) => {
    return Object.keys(PaymentStatuses).find(key => PaymentStatuses[key] === value);
};

const getPayStatus = () => {
    return  getPaymentStatus()
};

export { 
    PaymentStatuses, 
    getPaymentStatus, 
    PaymentStatus, 
    getPaymentStats, 
    getPayStatus 
};