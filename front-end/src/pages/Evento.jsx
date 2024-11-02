import { useParams } from "react-router-dom";
import { useFetch } from "../hooks/useFetch";
import { Header } from "../components/layout/Header";
import { ButtonGoBack } from "../components/layout/ButtonGoBack";
import { Footer } from "../components/layout/Footer";
import { EventCard } from "../components/cards/EventCard";
import { Registrationlist } from "../components/tables/Registrationlist";
import { Resources } from "../components/tables/Resources";
import "./Evento.css";

export function Evento() {
    const { id } = useParams();
    const { data: evento, loading, error } = useFetch(`/eventos/${id}`, "GET");

    return (
        <div className='evento'>
            <Header />
            <div className='event-body'>
                <ButtonGoBack />
                {evento && !loading && !error && <EventCard {...evento} />}
                <Registrationlist id={id} />
                {evento && !loading && !error && <Resources eventoId={evento.id} />}
            </div>
            <Footer />
        </div>
    );
}
