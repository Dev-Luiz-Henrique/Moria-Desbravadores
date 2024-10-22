import "./HeaderNoNav.css"
import MoriaLogo from "../assets/img/Logo.svg"
import HeaderBgImg from "../assets/img/Background_mountain01.svg"

export function HeaderNoNav() {
    return (
        <header className="landing-header-no-nav">
            <div>
                <img src={ MoriaLogo } alt="" />
                <h1>MORIÁ DESBRAVADORES</h1>
                <span>LOGIN</span>
            </div>
        </header>
    )
}