import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { apiRequest } from "../../utils/api";
import { useAuth } from "../../context/AuthContext";
import { useFetch } from "../../hooks/useFetch";
import { formatDateWithHours } from "../../utils/dateHelpers";
import { formatEndereco } from "../../utils/stringHelpers";
import "./CardEvents.css"

import DefaultEvent from "../../assets/img/default-event.png"
import ArrowRight from "../../assets/img/layout/arrow-right.svg"
import Delete from "../../assets/img/layout/delete.svg"
import Edit from "../../assets/img/layout/edit.svg"

export function CardEvents(
    { id, nome, endereco, logradouro, numero, dataInicio, descricao, atracao, onDelete }
) {
    const navigate = useNavigate();
    const { membro, isLoading } = useAuth();

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

    const handleEnroll = async () => {
        const { error: enrollError } = await apiRequest(`/inscricoes/confirmar?membroId=${membro.id}&eventoId=${id}`, "PUT");

        if (enrollError) {
            alert("Ocorreu um erro ao tentar se inscrever: " + enrollError);
            console.error("Erro ao tentar se inscrever: ", enrollError);
        } else {
            alert("Inscrição realizada com sucesso!");
        }
    };
            
    if (isLoading) return <p>Carregando dados do membro...</p>;
    if (!membro) return <p>Dados do membro não disponíveis.</p>;

    return (
        <div className="card-event-container">
            <img src={imageUrl} alt={nome} />
            <div className="card-events-buttons">
                <button onClick={handleDetail}>
                    <img src={ArrowRight} alt="Detalhes"/>
                </button>
                <button onClick={handleEdit}>
                    <img src={Edit} alt="Edit"/>
                </button>
                <button onClick={onDelete}>
                    <img src={Delete} alt="Delete"/>
                </button>
            </div>

            <div className="card-event-text">
                <h3>{nome}</h3>
                <span>
                    <b>ENDEREÇO:</b>
                    <p>{formatEndereco(endereco, logradouro, numero)}</p>
                </span>

                <span>
                    <b>DATA:</b>
                    <p>{formatDateWithHours(dataInicio)}</p>
                </span>

                <span>
                    <b>DESCRIÇÃO:</b>
                    <p>{descricao}</p>
                </span>

                <span>
                    <b>ATRAÇÃO:</b>
                    <p>{atracao}</p>
                </span>
                <span>
                    <button className="enroll-button" onClick={handleEnroll}>Inscrever-se</button>
                </span>
            </div>
        </div>
    );
}