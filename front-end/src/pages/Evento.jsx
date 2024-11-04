import { useParams } from "react-router-dom";
import { useFetch } from "../hooks/useFetch";
import { Header } from "../components/layout/Header";
import { ButtonGoBack } from "../components/layout/ButtonGoBack";
import { Footer } from "../components/layout/Footer";
import { Registrationlist } from "../components/tables/Registrationlist";
import { Resources } from "../components/tables/Resources";
import "./Evento.css";
import { CardEvents } from "../components/cards/CardEvents";

export function Evento() {
    const { id } = useParams();
    const { data: evento, loading, error } = useFetch(`/eventos/${id}`, "GET");

    return (
        <div className='evento'>
            <Header />
            <div className='event-body'>
                <ButtonGoBack />
                {evento && !loading && !error && <CardEvents {...evento} />}
                {evento && !loading && !error && <Registrationlist eventoId={evento.id} />}
                {evento && !loading && !error && <Resources eventoId={evento.id} />}
            </div>
            <Footer />
        </div>
    );
}
