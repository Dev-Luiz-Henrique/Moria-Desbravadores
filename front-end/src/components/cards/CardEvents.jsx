import { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { apiRequest } from "../../utils/api";
import { useAuth } from "../../context/AuthContext";
import { formatDateWithHours } from "../../utils/dateHelpers";
import { formatEndereco } from "../../utils/stringHelpers";
import { Authorities } from "../../utils/authorities";
import "./CardEvents.css"

import DefaultEvent from "../../assets/img/default-event.png"
import ArrowRight from "../../assets/img/layout/arrow-right.svg"
import Delete from "../../assets/img/layout/delete.svg"
import Edit from "../../assets/img/layout/edit.svg"

export function CardEvents(
    { id, nome, endereco, logradouro, numero, dataInicio, descricao, atracao, onDelete }
) {
    const navigate = useNavigate();
    const location = useLocation();
    const { membro, isLoading } = useAuth();

    const { authorities } = useAuth();
    const allowedAuthorities = [Authorities.DIRETOR_CLUBE, Authorities.DIRETOR_ASSOCIADO, Authorities.SECRETARIO];
    const hasAccess = allowedAuthorities.some(auth => authorities.includes(auth));

    const isDetailPage = location.pathname.includes('/detalhes-evento/');
    const [imageUrl, setImageUrl] = useState(DefaultEvent);
    useEffect(() => {
        const loadImage = async () => {
            try {
                const { data: img } = await apiRequest(`/eventos/${id}/imagem`, "GET", null, "arraybuffer");
                if (img)
                    setImageUrl(img);
            } catch (error) {
                console.error(`Erro ao carregar imagem do evento ${id}:`, error);
                setImageUrl(DefaultEvent);
            }
        };

        loadImage();
    }, [id]);

    const handleEdit = () => {
        navigate(`/cadastrar-evento/${id}`);
    };

    const handleDetail = () => {
        navigate(`/detalhes-evento/${id}`);
    };

    const handleDelete = async () => {
        if (!hasAccess) {
            alert("Você não tem permissão para excluir eventos.");
            return;
        }

        const confirmed = window.confirm(`Você tem certeza que deseja excluir o evento?`);
        if (confirmed) {
            const { error: deleteError } = await apiRequest(`/eventos/${id}`, "DELETE");
            if (deleteError) {
                console.error(deleteError);
                alert("Erro ao tentar excluir o evento.");
            } else {
                alert("Evento excluído com sucesso.");
                navigate("/eventos");
            }
        }
    };

    const handleEnroll = async () => {
        const { error: enrollError } = await apiRequest(`/inscricoes/confirmar?membroId=${membro.id}&eventoId=${id}`, "PUT");

        if (enrollError) {
            alert("Ocorreu um erro ao tentar se inscrever: " + enrollError);
            console.error("Erro ao tentar se inscrever: ", enrollError);
        } 
        else {
            alert("Inscrição realizada com sucesso!");
            // TODO: Arranjar outra forma de atualizar o componente de inscrição que não seja recarregar a página
            if (isDetailPage)
                window.location.reload();
        }
    };
            
    if (isLoading) return <p>Carregando dados do membro...</p>;
    if (!membro) return <p>Dados do membro não disponíveis.</p>;

    return (
        <div className={`card-event-container ${isDetailPage ? 'card-event-large' : ''}`}>
            <div className="card-events-img">
                <img src={imageUrl} alt={nome} />
            </div>
            
            {hasAccess && (
                <div className="card-events-buttons">
                    {/* <button onClick={handleDetail}>
                        <img src={ArrowRight} alt="Detalhes"/>
                    </button> */}
                    <button onClick={handleEdit}>
                        <img src={Edit} alt="Edit"/>
                    </button>
                    <button onClick={onDelete || handleDelete}>
                        <img src={Delete} alt="Delete"/>
                    </button>
                </div>
            )}
            
            <div className="card-event-text">
                <h3 onClick={handleDetail}>{nome}</h3>
                <span>
                    <b>ATRAÇÃO:</b>
                    <p>{atracao}</p>
                </span>

                <span>
                    <b>DATA:</b>
                    <p>{formatDateWithHours(dataInicio)}</p>
                </span>

                <span>
                    <b>ENDEREÇO:</b>
                    <p className={isDetailPage ? 'address-limited' : ''}>
                        {formatEndereco(endereco, logradouro, numero)}
                    </p>
                </span>

                <span>
                    <b>DESCRIÇÃO:</b>
                    <p className={isDetailPage ? 'description-limited' : ''}>
                        {descricao}
                    </p>
                </span>

                <span>
                    <button className="enroll-button" onClick={handleEnroll}>Inscrever-se</button>
                </span>
            </div>
        </div>
    );
}