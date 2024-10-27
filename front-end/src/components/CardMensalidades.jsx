import "./CardMensalidades.css"

export function CardMensalidades() {
    return (
        <section className="container-card-mensalidades">
            <p>01/10/2024</p>
            <div className="data-section-mensalidades">
                <div className="box-mensalidade">
                    <div className="status-mensalidade">
                        <div className="status-detail"></div>
                        <div className="status-text">
                            <p>PENDENTE</p>
                        </div>
                    </div>
                </div>
                <div className="valor-mensalidade">
                    <h3>VALOR:</h3>
                    <p>R$15,00</p>
                </div>
                <div className="forma-pagamento-mensalidade">
                    <h3>FORMA DE PAGAMENTO:</h3>
                    <p>Cartão de crédito</p>
                </div>
                <div className="vencimento-mensalidade">
                    <h3>VENCIMENTO:</h3>
                    <p>31/10/2024</p>
                </div>
                <div className="pagamento-mensalidade">
                    <h3>PAGAMENTO:</h3>
                    <p>---</p>
                </div>
                <div className="imprimir-comprovante-mensalidade">
                    <button className="imprimir-comprovante">
                        <h3>IMPRIMIR COMPROVANTE</h3>
                    </button>
                </div>
            </div>
        </section>
    )
}