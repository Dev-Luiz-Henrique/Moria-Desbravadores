import { useNavigate } from "react-router-dom";
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

    const handleEdit = () => {
        navigate(`/cadastrar-evento/${id}`);
    };

    const handleDetail = () => {
        navigate(`/detalhes-evento/${id}`);
    };

    return (
        <div className="card-event-container">
            <img src={DefaultEvent} alt={nome} />
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
            </div>
        </div>
    );
}