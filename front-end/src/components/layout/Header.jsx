import { useEffect, useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import { useAuth } from "../../context/AuthContext"
import { DropDownProfile } from "./DropDownProfile"
import "./Header.css"

import MoriaLogo from "../../assets/img/layout/logo.svg"
import HeaderBgImg from "../../assets/img/backgrounds/bg-refact.svg"
import ProfileLogo from "../../assets/img/layout/profile-icon.svg"

export function Header() {
    const { token, isLoading } = useAuth();
    const location = useLocation();
    const [showProfile, setShowProfile] = useState(false);
    const isHome = location.pathname === '/';

    // TODO: Refactor this timer logic to use a loading state instead. 
    // Implement a more robust solution that checks for member data readiness 
    // before displaying the dropdown profile, eliminating the need for a fixed delay.
    useEffect(() => {
        if (token) {
            const timer = setTimeout(() => {
                setShowProfile(true);
            }, 100);

            return () => clearTimeout(timer);
        } 
        else setShowProfile(false);
    }, [token]);

    return (
        <header className={`header-container ${isHome ? 'with-nav' : 'no-nav'}`}>
            <div>
                <Link to="/">
                    <img src={MoriaLogo} alt="Logo do Clube Moriá" />
                </Link>
                <h1>MORIÁ DESBRAVADORES</h1>
                {token && showProfile ? (
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
                        <li><a href="#events">Eventos Públicos</a></li>
                        <li><a href="#past-events">Eventos Passados</a></li>
                        <li><a href="#contact">Contato</a></li>
                    </ul>
                </nav>
            )}
            {isHome && <img src={HeaderBgImg} alt="" className="header-bg-detail" />}
        </header>
    );
};