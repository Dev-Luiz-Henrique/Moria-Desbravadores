import "./HeaderSignedInNoNav.css"
import MoriaLogo from "../assets/img/Logo.svg"
import HeaderBgImg from "../assets/img/Background_mountain01.svg"
import ProfileLogo from "../assets/img/profile-icon.svg"
import { DropDownProfile } from "./DropDownProfile"

export function HeaderSignedInNoNav() {
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
        </header>
        
    )
}