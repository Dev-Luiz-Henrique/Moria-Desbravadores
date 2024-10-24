import { CardUser } from "./CardUser";
import { Footer } from "./Footer";
import { HeaderSignedInNoNav } from "./HeaderSigneInNoNav";
import { useFetch } from "../hooks/useFetch";
import { apiRequest } from "../utils/api";
import "./UserPage.css";

import AddIcon from "../assets/img/Plus.svg";
import SearchIcon from "../assets/img/Glass.svg";

export function UserPage() {
    const { data: membros, setData: setMembros, loading, error } = useFetch("/membros", "GET");

    const handleDelete = async (id) => {
        const confirmed = window.confirm("Você tem certeza que deseja excluir este membro?");
        if (confirmed) {
            const { error: deleteError } = await apiRequest(`/membros/${id}`, "DELETE");

            if (deleteError)
                console.error(deleteError);
            else 
                setMembros((prevMembros) => prevMembros.filter(membro => membro.id !== id));
        }
    };

    if (loading) return <p>Carregando...</p>;
    if (error) return <p>Erro ao carregar os membros: {error}</p>;

    return (
        <>
            <HeaderSignedInNoNav />
            <section className="container-user-page">
                <div className="filter">
                    <div className="detail">
                        <button>
                            <img src={AddIcon} alt="AddIcon" />
                            <p>CADASTRAR MEMBRO</p>
                        </button>
                        <div className="search">
                            <img src={SearchIcon} alt="SearchIcon" />
                            <input type="text" />
                        </div>
                    </div>
                </div>

                <div className="users-space">
                    <div className="users">
                        {membros.map((membro) => (
                            <CardUser
                                key={membro.id}
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