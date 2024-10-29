import DefaultEvent from "../assets/img/default-event.png"
import Delete from "../assets/img/Delete.svg"
import Edit from "../assets/img/Edit.svg"
import "./CardEvents.css"

export function CardEvents() {
    return (
        <div className="card-event-container">
            <img src={DefaultEvent} alt="" />
            <div className="card-events-buttons">
                <button><img src={Edit} alt="" /></button>
                <button><img src={Delete} alt="" /></button>
            </div>
            <div className="card-event-text">
                <h3>CAMPORI 2024</h3>

                <span>
                    <b>ENDEREÇO:</b>
                    <p>IABC Planalmira - GO</p>
                </span>

                <span>
                    <b>DATA:</b>
                    <p>24-28 de Julho</p>
                </span>

                <span>
                    <b>DESCRIÇÃO:</b>
                    <p>Reunião anual do clube dos Desbravadores</p>
                </span>

                <span>
                    <b>ATRAÇÃO:</b>
                    <p>---</p>
                </span>
            </div>
        </div>
    )
}