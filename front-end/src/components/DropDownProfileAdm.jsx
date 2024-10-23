import ProfileLogo from "../assets/img/profile-icon.svg"
import "./DropDownProfileAdm.css"

export function DropDownProfileAdm() {
    return(
        <div className="header-signed-in-container">
            <div className="hsc1">
                <h2>Luiz Henrique de Santana</h2>
                <img src={ProfileLogo} alt="" />
            </div>
            <div className="hsc2">
                <a href="#"><p>Mensalidades</p></a>
                <a href="#"><p>Eventos Privados</p></a>
                <a href="#"><p>Gerenciar Membros</p></a>
                <a href="#"><p>Gerenciar Eventos</p></a>
                <span>NOTIFICAÇÕES</span>
            </div>
            <div className="notificacao">
                <span>12/10/2024</span>
                <p>Inscrição para Campori 2024 Aprovada.</p>
            </div>
            <div className="hsc4">Não há notificações</div> 
        </div>
    )
}