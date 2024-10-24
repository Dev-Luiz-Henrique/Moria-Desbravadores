import { useState, useEffect } from "react";
import { CardUser } from "./CardUser";
import { Footer } from "./Footer";
import { HeaderSignedInNoNav } from "./HeaderSigneInNoNav";
import { useFetch } from "../hooks/useFetch";
import "./UserPage.css";

import AddIcon from "../assets/img/Plus.svg";
import SearchIcon from "../assets/img/Glass.svg";

export function UserPage() {
    const [deleteId, setDeleteId] = useState(null);
    const [membros, setMembros] = useState([]);

    const { data: fetchedMembros, loading, error } = useFetch("/membros", "GET");
    const { loading: deleting, error: deleteError } = useFetch(
        deleteId ? `/membros/${deleteId}` : null, deleteId ? "DELETE" : null
    );

    useEffect(() => {
        if (fetchedMembros) setMembros(fetchedMembros);
    }, [fetchedMembros]);

    useEffect(() => {
        if (deleteId) {
            setMembros((prevMembros) => prevMembros.filter(membro => membro.id !== deleteId));
            setDeleteId(null);
        }
    }, [deleteId]);

    if (loading) return <p>Carregando...</p>;
    if (error) return <p>Erro ao carregar os membros: {error.message}</p>;

    const handleDelete = (id) => {
        const confirmed = window.confirm("Você tem certeza que deseja excluir este membro?");
        if (confirmed) setDeleteId(id);
    };

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