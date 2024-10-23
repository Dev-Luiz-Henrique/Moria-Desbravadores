import { CardUser } from "./CardUser"
import "./UserPage.css"
import AddIcon from "../assets/img/Plus.svg"
import SearchIcon from "../assets/img/Glass.svg"
import { HeaderSignedIn } from "./HeaderSignedIn"
import { Footer } from "./Footer"

export function UserPage() {
    return (
        <>
            <HeaderSignedIn />
            <section className="container-user-page">
                <div className="filter">
                    <div className="detail">
                        <button>
                            <img src={AddIcon} alt="" />
                            <p>CADASTRAR MEMBRO</p>
                        </button>
                        <div className="search">
                            <img src={SearchIcon} alt="" />
                            <input type="text" />
                        </div>
                        {/* <div className="filter-users">
                        <span>
                            <img src="" alt="" />
                            <p>FILTROS</p>
                        </span>
                    </div> */}
                    </div>
                </div>

                <div className="users-space">
                    <div className="users">
                        <CardUser />
                        <CardUser />
                        <CardUser />
                        <CardUser />
                    </div>
                </div>
            </section>
            <Footer />
        </>
    )
}