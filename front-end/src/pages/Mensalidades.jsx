import { CardMensalidades } from "../components/CardMensalidades";
import { Footer } from "../components/Footer";
import { Header } from "../components/header";
import "./Mensalidades.css"

export function Mensalidades() {
    return (
        <>
            <Header />
            <section className="mensalidades-page-container">
                <div>
                    <CardMensalidades />
                    <CardMensalidades />
                    <CardMensalidades />
                    <CardMensalidades />
                    <CardMensalidades />
                </div>
            </section>
            <Footer />
        </>
    )
}