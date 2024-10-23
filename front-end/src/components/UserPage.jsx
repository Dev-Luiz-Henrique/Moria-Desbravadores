import { CardUser } from "./CardUser"
import "./UserPage.css"

export function UserPage() {
    return(
        <section className="container-user-page">
            <div className="filter">
                <div className="detail">
                    <button>
                        <img src="" alt="" />
                        <p>CADASTRAR MEMBRO</p>
                    </button>
                    <div className="search"></div>
                    <div className="filter-users">
                        <span>
                            <img src="" alt="" />
                            <p>FILTROS</p>
                        </span>                        
                    </div>
                </div>
            </div>

            <div className="users">
                <CardUser />
                <CardUser />
                <CardUser />
                <CardUser />
            </div>
        </section>
    )
}