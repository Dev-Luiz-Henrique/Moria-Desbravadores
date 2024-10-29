import { useAuth } from "../context/AuthContext";
import { useFetch } from "../hooks/useFetch";
import { apiRequest } from "../utils/api";
import { Authorities } from "../utils/authorities";
import AddIcon from "../assets/img/Plus.svg";
import SearchIcon from "../assets/img/Glass.svg";
import "./ManageEvents.css"
import { CardEvents } from "../components/CardEvents";
import { Header } from "../components/header";
import { Footer } from "../components/Footer";

export function ManageEvents() {
    const { authorities } = useAuth();
    const allowedAuthorities = [Authorities.DIRETOR_CLUBE, Authorities.DIRETOR_ASSOCIADO, Authorities.SECRETARIO];
    const hasAccess = allowedAuthorities.some(auth => authorities.includes(auth));

    const { data: eventos, setData: setEventos, loading, error } = useFetch("/eventos", "GET");

    const handleDelete = async (id) => {
        if (!hasAccess) {
            alert("Você não tem permissão para excluir membros.");
            return;
        }

        const confirmed = window.confirm("Você tem certeza que deseja excluir este membro?");
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
                <div className="filter">
                    <div className="detail">
                        <button>
                            <img src={AddIcon} alt="AddIcon" />
                            <p>CRIAR EVENTO</p>
                        </button>
                        <div className="search">
                            <img src={SearchIcon} alt="SearchIcon" />
                            <input type="text" />
                        </div>
                    </div>
                </div>

                <div className="events-space">
                    <div className="events">
                        {!error && !loading && eventos.map((evento) => (
                            <CardEvents 
                                key={evento.id}
                                nome={evento.nome}
                                endereco={evento.endereco}
                                logradouro={evento.logradouro}
                                numero={evento.numero}
                                dataInicio={evento.dataInicio}
                                descricao={evento.descricao}
                                atracao={evento.atracao}
                                onDelete={() => handleDelete(eventos.id)}
                            />
                        ))}
                    </div>
                </div>
            </section>
            <Footer />
        </>
    )
}