import { ButtonGoBack } from "../components/ButtonGoBack";
import { Footer } from "../components/Footer";
import { Header } from "../components/header";
import { EventoDataSignUp } from "../components/EventoDataSignUp";
import "./EventoRegisterPage.css"

export function EventoRegisterPage() {
    return (
        <>
            <Header />
            <div className="container-register-evento-page">
                <div className="return-btn"><ButtonGoBack /></div>
                    <EventoDataSignUp initialData={{
                        "nome": "Festival de Música",
                        "atracao": "Banda XYZ",
                        "descricao": "Um festival de música com várias bandas locais.",
                        "imagem": "imagem_festival.jpg",
                        "dataInicio": "2024-11-15T18:00:00",
                        "dataFim": "2024-11-15T23:00:00",
                        "numero": 170,
                        "logradouro": "Viela Terra Rica",
                        "publico": true,
                        "endereco": {
                            "cep": "07074170",
                            "bairro": "Vila Rosália",
                            "cidade": "Guarulhos",
                            "estado": "SP"
                        }
                    }}/>
                </div>
            <Footer />
        </>
    )
}