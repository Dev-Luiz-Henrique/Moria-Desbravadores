import { useAuth } from "../context/AuthContext";
import { useFetch } from "../hooks/useFetch";
import { apiRequest } from "../utils/api";
import { Authorities } from "../utils/authorities";
import { CardUser } from "./CardUser";
import { Footer } from "./Footer";
import "./UserPage.css";

import AddIcon from "../assets/img/Plus.svg";
import SearchIcon from "../assets/img/Glass.svg";
import { Link } from "react-router-dom";
import { Header } from "./header";

export function UserPage() {
    const { authorities } = useAuth();
    const allowedAuthorities = [Authorities.DIRETOR_CLUBE, Authorities.DIRETOR_ASSOCIADO, Authorities.SECRETARIO];
    const hasAccess = allowedAuthorities.some(auth => authorities.includes(auth));

    const { data: membros, setData: setMembros, loading, error } = useFetch("/membros", "GET");

    const handleDelete = async (id) => {
        if (!hasAccess) {
            alert("Você não tem permissão para excluir membros.");
            return;
        }

        const confirmed = window.confirm("Você tem certeza que deseja excluir este membro?");
        if (confirmed) {
            const { error: deleteError } = await apiRequest(`/membros/${id}`, "DELETE");

            if (deleteError)
                console.error(deleteError);
            else 
                setMembros((prevMembros) => prevMembros.filter(membro => membro.id !== id));
        }
    };

    return (
        <>
            <Header />
            <section className="container-user-page">
                <div className="filter">
                    <div className="detail">
                        <Link to="/cadastrar-membro">
                            <button>
                                <img src={AddIcon} alt="AddIcon" />
                                <p>CADASTRAR MEMBRO</p>
                            </button>
                        </Link>
                        <div className="search">
                            <img src={SearchIcon} alt="SearchIcon" />
                            <input type="text" />
                        </div>
                    </div>
                </div>

                <div className="users-space">
                    <div className="users">
                        {!error && !loading && membros.map((membro) => (
                            <CardUser
                                key={membro.id}
                                id={membro.id}
                                nome={membro.nome}
                                tipo={membro.tipo}
                                status={membro.status}
                                mensalidade={membro.mensalidade}
                                onDelete={() => handleDelete(membro.id)}
                            />
                        ))}
                    </div>
                </div>
            </section>
            <Footer />
        </>
    );
}