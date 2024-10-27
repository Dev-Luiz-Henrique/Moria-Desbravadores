import { formatDate } from "../utils/dateHelpers";
import "./CardMensalidades.css";

export function CardMensalidades({
    valor,
    data,
    dataPagamento,
    dataVencimento,
    pagamentoRealizado,
    formaPagamento,
    onPrintComprovante
}) {
    return (
        <section className='container-card-mensalidades'>
            <p>{formatDate(data)}</p>
            <div className='data-section-mensalidades'>
                <div className='box-mensalidade'>
                    <div className='status-mensalidade'>
                        <div className='status-detail'></div>
                        <div className='status-text'>
                            <p>{pagamentoRealizado ? "PAGO" : "PENDENTE"}</p>
                        </div>
                    </div>
                </div>
                <div className='valor-mensalidade'>
                    <h3>VALOR:</h3>
                    <p>R$ {valor.toFixed(2).replace('.', ',')}</p>
                </div>
                <div className='forma-pagamento-mensalidade'>
                    <h3>FORMA DE PAGAMENTO:</h3>
                    <p>{formaPagamento}</p>
                </div>
                <div className='vencimento-mensalidade'>
                    <h3>VENCIMENTO:</h3>
                    <p>{formatDate(dataVencimento)}</p>
                </div>
                <div className='pagamento-mensalidade'>
                    <h3>PAGAMENTO:</h3>
                    <p>{dataPagamento ? formatDate(dataPagamento) : "---"}</p>
                </div>
                <div className='imprimir-comprovante-mensalidade'>
                    <button className='imprimir-comprovante' onClick={onPrintComprovante}>
                        <h3>IMPRIMIR COMPROVANTE</h3>
                    </button>
                </div>
            </div>
        </section>
    );
}