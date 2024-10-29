import { useNavigate } from "react-router-dom";
import "./CardUser.css";
import EditImg from "../assets/img/Edit.svg";
import DeleteImg from "../assets/img/Delete.svg";
import UserICon from "../assets/img/user-card-icon.svg";

export function CardUser({ id, nome, tipo, status, mensalidade, onDelete }) {
    const navigate = useNavigate();

    const handleEdit = () => {
        navigate(`/cadastrar-membro/${id}`);
    };

    return (
        <div className="card-user-container">
            <div className="options">
                <button onClick={handleEdit} ><img src={EditImg} alt="Editar" /></button>
                <button onClick={onDelete}><img src={DeleteImg} alt="Deletar" /></button>
            </div>

            <div className="user-name">
                <img src={UserICon} alt="Ícone do usuário" />
                <h3>{nome}</h3>
            </div>

            <div className="user-tipo">
                <b>TIPO:</b>
                <span>{tipo}</span>
            </div>

            <div className="section-status-mensalidade">
                <div className="ssm user-status">
                    <p>STATUS:</p>
                    <span className="situation">{status}</span>
                </div>
                <div className="ssm user-mensalidade">
                    <p>MENSALIDADE:</p>
                    <span className="situation">{mensalidade}</span>
                </div>
            </div>
        </div>
    );
}