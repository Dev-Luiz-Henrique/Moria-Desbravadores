import { Link } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import { Authorities, isVoluntario } from "../utils/authorities";
import { abbreviateName } from "../utils/stringHelpers";
import "./DropDownProfile.css";

import ProfileLogo from "../assets/img/profile-icon.svg";

export function DropDownProfile() {
    const { membro, authorities, logout, isLoading } = useAuth();
    const hasManageAccess = isVoluntario(authorities);

    if (isLoading)
        return <div>Carregando perfil...</div>;
    if (!membro)
        return null;

    return(
        <div className="dropdown-profile-container">
            <div className="hsc1">
                <h2>{abbreviateName(membro.nome)}</h2>
                <Link to="/membro">
                    <img src={ProfileLogo} alt="Icone do perfil" />
                </Link>
            </div>
            <div className="hsc2">
                <Link to="/eventos"><p>Eventos</p></Link>
                <Link to="/mensalidades"><p>Mensalidades</p></Link>
                {
                    hasManageAccess && (
                        <>
                            <Link to="/gerenciar-membros"><p>Gerenciar Membros</p></Link>
                            <Link to="/gerenciar-mensalidades"><p>Gerenciar Mensalidades</p></Link>
                            <Link to="/gerenciar-eventos"><p>Gerenciar Eventos</p></Link>
                        </>
                    )
                }
                <span>NOTIFICAÇÕES</span>
            </div>
            {/*<div className="notificacao">
                <span>12/10/2024</span>
                <p>Inscrição para Campori 2024 Aprovada.</p>
            </div>*/}
            <div className="hsc4">Não há notificações</div> 
        </div>
    )
}