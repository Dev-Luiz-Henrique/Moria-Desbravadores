import { Link } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import { useFetch } from "../hooks/useFetch";
import { apiRequest } from "../utils/api";
import { Authorities } from "../utils/authorities";
import { CardEvents } from "../components/cards/CardEvents";
import { Header } from "../components/layout/Header";
import { Footer } from "../components/layout/Footer";
import "./ManageEvents.css";

import AddIcon from "../assets/img/layout/plus.svg";
import SearchIcon from "../assets/img/layout/search.svg";

export function ManageEvents() {
    const { authorities } = useAuth();
    const allowedAuthorities = [Authorities.DIRETOR_CLUBE, Authorities.DIRETOR_ASSOCIADO, Authorities.SECRETARIO];
    const hasAccess = allowedAuthorities.some(auth => authorities.includes(auth));

    const { data: eventos, setData: setEventos, loading, error } = useFetch("/eventos", "GET");

    const handleDelete = async (id) => {
        if (!hasAccess) {
            alert("Você não tem permissão para excluir eventos.");
            return;
        }

        const confirmed = window.confirm("Você tem certeza que deseja excluir este evento? ");
        if (confirmed) {
            const { error: deleteError } = await apiRequest(`/eventos/${id}`, "DELETE");

            if (deleteError)
                console.error(deleteError);
            else 
                setEventos((prevEventos) => prevEventos.filter(evento => evento.id !== id))
        }
    };

    return (
        <>
            <Header />
            <section className="container-manage-events-page">
                { hasAccess && (
                        <div className="filter">
                        <div className="detail">
                            <Link to="/cadastrar-evento">
                                <button>
                                    <img src={AddIcon} alt="AddIcon" />
                                    <p>CRIAR EVENTO</p>
                                </button>
                            </Link>
    
                        </div>
                    </div>
                    )
                }
            
                <div className="events-space">
                    <div className="events">
                        {!error && !loading && eventos.map((evento) => (
                            <CardEvents 
                                key={evento.id}
                                id={evento.id}
                                nome={evento.nome}
                                endereco={evento.endereco}
                                logradouro={evento.logradouro}
                                numero={evento.numero}
                                dataInicio={evento.dataInicio}
                                descricao={evento.descricao}
                                atracao={evento.atracao}
                                onDelete={() => handleDelete(evento.id)}
                            />
                        ))}
                    </div>
                </div>
            </section>
            <Footer />
        </>
    )
}
