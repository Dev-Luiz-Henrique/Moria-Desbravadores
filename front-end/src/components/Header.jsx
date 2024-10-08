import "./Header.css"
import MoriaLogo from "../assets/img/Logo.svg"
import HeaderBgImg from "../assets/img/Background_mountain01.svg"

export function Header() {
    return (
        <header className="landing-header">
            <div>
                <img src={ MoriaLogo } alt="" />
                <h1>MORIÁ DESBRAVADORES</h1>
                <span>LOGIN</span>
            </div>
            
            <nav>
                <ul>
                    <li><a href="">Eventos Públicos</a></li>
                    <li><a href="">Contato</a></li>
                    <li><a href="">Sobre nós</a></li>
                </ul>
            </nav>

            <img src={HeaderBgImg} alt="" className="header-bg-detail"/>
        </header>
    )
}