import { useAuth } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";
import { apiRequest } from "../utils/api";
import { Authorities } from "../utils/authorities";
import ImgEvent from "../assets/img/default-event.png";
import EditImg from "../assets/img/Edit.svg";
import DeleteImg from "../assets/img/Delete.svg";
import "./EventCard.css";
import { formatEndereco } from "../utils/stringHelpers";
import { formatDateWithHours } from "../utils/dateHelpers";

export function EventCard(evento) {
    const navigate = useNavigate();
    const { authorities } = useAuth();
    const allowedAuthorities = [Authorities.DIRETOR_CLUBE, Authorities.DIRETOR_ASSOCIADO, Authorities.SECRETARIO];
    const hasAccess = allowedAuthorities.some(auth => authorities.includes(auth));

    const handleDelete = async () => {
        if (!hasAccess) {
            alert("Você não tem permissão para excluir eventos.");
            return;
        }

        const confirmed = window.confirm("Você tem certeza que deseja excluir este evento?");
        if (confirmed) {
            const { error: deleteError } = await apiRequest(`/eventos/${evento.id}`, "DELETE");

            if (deleteError)
                console.error(deleteError);
            navigate("/gerenciar-eventos");
        }
        
    };

    const handleEdit = () => {
        if (!hasAccess) {
            alert("Você não tem permissão para editar eventos.");
            return;
        }
        navigate(`/cadastrar-evento/${evento.id}`);
    };

    return (
        <article className='event-card box-shadow'>
            <img className='event-img' src={ImgEvent} alt='Imagem do Evento' />
            <div className='event-info'>
                {hasAccess && (
                    <div className='options2'>
                        <button type='button' onClick={handleEdit}>
                            <img src={EditImg} alt='Editar' />
                        </button>
                        <button type='button' onClick={handleDelete}>
                            <img src={DeleteImg} alt='Deletar' />
                        </button>
                    </div>
                )}
                <h3>{evento.nome}</h3>
                <p>
                    <b>ENDEREÇO:</b> {formatEndereco(evento.endereco, evento.logradouro, evento.numero)}
                </p>
                <p>
                    <b>DATA:</b> {formatDateWithHours(evento.dataInicio)}
                </p>
                <p>
                    <b>DESCRIÇÃO:</b> {evento.descricao}
                </p>
                <p>
                    <b>ATRAÇÃO:</b> {evento.atracao}
                </p>
            </div>
        </article>
    );
}