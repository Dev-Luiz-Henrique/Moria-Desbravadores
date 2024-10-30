import { useNavigate } from "react-router-dom";
import "./CardUser.css";
import EditImg from "../assets/img/Edit.svg";
import DeleteImg from "../assets/img/Delete.svg";
import UserICon from "../assets/img/user-card-icon.svg";
import { abbreviateName, normalizeUnderscore } from "../utils/stringHelpers";
import { Link } from "react-router-dom";
import { useFetch } from "../hooks/useFetch";

export function CardUser({ id, nome, tipo, status, onDelete }) {
    const navigate = useNavigate();

    const handleEdit = () => {
        navigate(`/cadastrar-membro/${id}`);
    };

    const handleDetail = () => {
        navigate(`/membro/${id}`);
    };

    return (
        <div className="card-user-container">
            <div className="options">
                <button onClick={handleEdit} ><img src={EditImg} alt="Editar" /></button>
                <button onClick={onDelete}><img src={DeleteImg} alt="Deletar" /></button>
            </div>

            <Link to={`/membro/${id}`}>
                <div className="user-name">
                    <img src={UserICon} alt="Ícone do usuário" />
                    <h3>{abbreviateName(nome)}</h3>
                </div>
            </Link>
            
            <div className="user-tipo">
                <b>TIPO:</b>
                <span>{normalizeUnderscore(tipo)}</span>
            </div>

            <div className="section-status-mensalidade">
                <div className="ssm user-status">
                    <p>STATUS:</p>
                    <span className="situation">{status ? "ATIVO" : "INATIVO"}</span>
                </div>
                {/* <div className="ssm user-mensalidade">
                    <p>MENSALIDADE:</p>
                    <span className="situation">{latePayment ? "Atrasada" : "Em dia"}</span>
                </div> */}
            </div>
        </div>
    );
}