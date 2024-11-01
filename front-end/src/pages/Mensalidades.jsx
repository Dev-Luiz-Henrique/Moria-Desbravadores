import { useAuth } from "../context/AuthContext";
import { useFetch } from "../hooks/useFetch";
import { apiRequest } from "../utils/api";
import { Authorities } from "../utils/authorities";
import { CardMensalidades } from "../components/cards/CardMensalidades";
import { Footer } from "../components/layout/Footer";
import { Header } from "../components/layout/Header";
import "./Mensalidades.css"

export function Mensalidades() {
    const { authorities } = useAuth();
    const allowedAuthorities = [Authorities.DIRETOR_CLUBE, Authorities.DIRETOR_ASSOCIADO, Authorities.TESOUREIRO];
    const hasAccess = allowedAuthorities.some(auth => authorities.includes(auth));

    const { data: mensalidades, setData: setMensalidades, loading, error } = useFetch("/mensalidades", "GET");

    return (
        <>
            <Header />
            <section className="mensalidades-page-container">
                <div>
                    {!error && !loading && mensalidades.map((mensalidade) => (
                        <CardMensalidades
                            key={mensalidade.id}
                            valor={mensalidade.valor}
                            data={mensalidade.data}
                            dataPagamento={mensalidade.dataPagamento}
                            dataVencimento={mensalidade.dataVencimento}
                            pagamentoRealizado={mensalidade.pagamentoRealizado}
                            formaPagamento={mensalidade.formaPagamento}
                            onPrintComprovante={() => handlePrintComprovante(mensalidade.id)}
                        />
                    ))}
                </div>
            </section>
            <Footer />
        </>
    )
}
