import { Link, useLocation } from 'react-router-dom';
import { useAuth } from "../context/AuthContext"
import { DropDownProfile } from "./DropDownProfile"
import "./Header.css"

import MoriaLogo from "../assets/img/Logo.svg"
import HeaderBgImg from "../assets/img/Background_mountain01.svg"
import ProfileLogo from "../assets/img/profile-icon.svg"

export function Header() {
    const location = useLocation();
    const { token } = useAuth();
    const isHome = location.pathname === '/';

    return (
        <header className={`header-container ${isHome ? 'with-nav' : 'no-nav'}`}>
            <div>
                <Link to="/">
                    <img src={ MoriaLogo } alt="Logo do Clube Moriá" />
                </Link>
                <h1>MORIÁ DESBRAVADORES</h1>
                {token ? ( 
                    <>
                        <img src={ProfileLogo} alt="" className="img-profile" />
                        <div className="dropdown-profile">
                            <DropDownProfile />
                        </div>
                    </>
                ) : (
                    <Link to="/login">
                        <span>LOGIN</span>
                    </Link>
                )}
            </div>
            {isHome && (
                <nav>
                    <ul>
                        <li><a href="">Eventos Públicos</a></li>
                        <li><a href="">Contato</a></li>
                        <li><a href="">Sobre nós</a></li>
                    </ul>
                </nav>
            )}
            {isHome && <img src={HeaderBgImg} alt="" className="header-bg-detail"/>}
        </header>
    );
};