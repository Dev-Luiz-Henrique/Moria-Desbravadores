import "./HeaderSignedIn.css"
import MoriaLogo from "../assets/img/Logo.svg"
import HeaderBgImg from "../assets/img/Background_mountain01.svg"
import ProfileLogo from "../assets/img/profile-icon.svg"
import { DropDownProfile } from "./DropDownProfile"

export function HeaderSignedIn() {
    return (
        <header className="signed-in-header">
            <div>
                <img src={MoriaLogo} alt="" />
                <h1>MORIÁ DESBRAVADORES</h1>
                <img src={ProfileLogo} alt="" className="img-profile" />
                <div className="dropdown-profile">
                    <DropDownProfile />
                </div>
            </div>

            <nav>
                <ul>
                    <li><a href="">Eventos Públicos</a></li>
                    <li><a href="">Contato</a></li>
                    <li><a href="">Sobre nós</a></li>
                </ul>
            </nav>

            <img src={HeaderBgImg} alt="" className="header-bg-detail" />
        </header>
        
    )
}