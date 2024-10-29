import DefaultEvent from "../assets/img/default-event.png"
import Delete from "../assets/img/Delete.svg"
import Edit from "../assets/img/Edit.svg"
import { formatDateWithHours } from "../utils/dateHelpers";
import { formatEndereco } from "../utils/stringHelpers";
import "./CardEvents.css"

export function CardEvents({ nome, endereco, logradouro, numero, dataInicio, descricao, atracao, onDelete }) {
    return (
        <div className="card-event-container">
            <img src={DefaultEvent} alt={nome} />
            <div className="card-events-buttons">
                <button><img src={Edit} alt="Edit" /></button>
                <button onClick={onDelete}><img src={Delete} alt="Delete" /></button>
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