import "./CardUser.css"
import EditImg from "../assets/img/Edit.svg"
import DeleteImg from "../assets/img/Delete.svg"
import UserICon from "../assets/img/user-card-icon.svg"

export function CardUser() {
    return (
        <div className="card-user-container">
            <div className="options">
                <button><img src={EditImg} alt="" /></button>
                <button><img src={DeleteImg} alt="" /></button>
            </div>

            <div className="user-name">
                <img src={UserICon} alt="" />
                <h3>Luiz Henrique de Santana</h3>
            </div>

            <div className="user-tipo">
                <b>TIPO:</b>
                <span>Desbravador</span>
            </div>

            <div className="section-status-mensalidade">
                <div className="ssm user-status">
                    <p>STATUS:</p>
                    
                    <span className="situation">ATIVO</span>
                </div>
                <div className="ssm user-mensalidade">
                    <p>MENSALIDADE:</p>
                    <span className="situation">PENDENTE</span>
                </div>
            </div>
        </div>
    )
}