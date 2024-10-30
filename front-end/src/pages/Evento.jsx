import { useParams } from "react-router-dom";
import { useFetch } from "../hooks/useFetch";
import { Header } from "../components/header";
import { ButtonGoBack } from "../components/ButtonGoBack";
import { Footer } from "../components/Footer";
import { EventCard } from "../components/EventCard";
import { Registrationlist } from "../components/Registrationlist";
import { Resources } from "../components/Resources";
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
                <Resources />
            </div>
            <Footer />
        </div>
    );
}