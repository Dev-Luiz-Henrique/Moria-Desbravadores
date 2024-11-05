import { useState, useEffect, useRef } from "react";
import { useAuth } from "../../context/AuthContext";
import { useFetch } from "../../hooks/useFetch";
import { apiRequest } from "../../utils/api";
import { normalizeUnderscore } from "../../utils/stringHelpers";
import "./Registrationlist.css";

import DeleteImg from "../../assets/img/layout/delete.svg";

export function Registrationlist({ eventoId }) {
    const { authorities } = useAuth();
    const allowedAuthorities = ["DIRETOR_CLUBE", "DIRETOR_ASSOCIADO", "SECRETARIO"];
    const hasAccess = allowedAuthorities.some(auth => authorities.includes(auth));

    const [isFocused, setIsFocused] = useState(false);
    const [searchTerm, setSearchTerm] = useState("");
    const [errorMessage, setErrorMessage] = useState("");
    const [selectedMemberId, setSelectedMemberId] = useState(null);
    const [filteredMembros, setFilteredMembros] = useState([]);

    const inputRef = useRef(null);
    const suggestionsRef = useRef(null);

    const { data: inscricoes, setData: setInscricoes, loading: inscricoesLoading, 
        error: inscricoesError } = useFetch(`/inscricoes/evento/${eventoId}`, "GET");
    if (inscricoesError)
        setErrorMessage("Erro ao carregar inscrições. Tente novamente mais tarde.");

    const { data: membros, loading: membrosLoading, error: membrosError } = useFetch("/membros", "GET");
    if (membrosError)
        setErrorMessage("Erro ao carregar membros. Tente novamente mais tarde.");

    const handleClickOutside = (event) => {
        if (
            inputRef.current && !inputRef.current.contains(event.target) &&
            suggestionsRef.current && !suggestionsRef.current.contains(event.target)
        ) {
            setIsFocused(false); // Oculta a lista de sugestões ao clicar fora
        }
    };

    useEffect(() => {
        document.addEventListener("mousedown", handleClickOutside);
        return () => {
            document.removeEventListener("mousedown", handleClickOutside);
        };
    }, []);

    useEffect(() => {
        if (membros) {
            const filtered = membros.filter(member =>
                member.nome.toLowerCase().includes(searchTerm.toLowerCase())
            );
            setFilteredMembros(filtered);
        }
    }, [searchTerm, membros]);

    const handleDelete = async (id) => {
        if (!hasAccess) {
            alert("Você não tem permissão para excluir inscrições.");
            return;
        }

        const confirmed = window.confirm("Você tem certeza que deseja excluir esta inscrição?");
        if (confirmed) {
            const { error: deleteError } = await apiRequest(`/inscricoes/${id}`, "DELETE");

            if (deleteError) {
                setErrorMessage("Erro ao excluir a inscrição. Tente novamente mais tarde.");
                console.error(deleteError);
            }
            else
                setInscricoes(prevInscricoes => prevInscricoes.filter(inscricao => inscricao.id !== id));
        }
    };

    const handleAddMember = async (membroId) => {
        if (!membroId) return;

        const { data: newInscricao, error: addError } = await apiRequest(`/inscricoes`, "POST", {
            evento: {
                id: eventoId
            },
            membro: {
                id: membroId
            },
            statusParticipacao: "PENDENTE",
            inscrito: false
        });

        if (addError)
            setErrorMessage("Erro ao adicionar o membro. Tente novamente mais tarde.");
        else {
            setInscricoes(prevInscricoes => [...prevInscricoes, newInscricao]);
            setSelectedMemberId(null);
            setSearchTerm("");
        }
    };

    const handleSelectMember = (membro) => {
        if (!hasAccess) {
            alert("Você não tem permissão para adicionar membros.");
            return;
        }

        setSelectedMemberId(membro.id);
        setSearchTerm(membro.nome);
        handleAddMember(membro.id);
        setIsFocused(false);
    };

    return (
        <section className="box-shadow">
            {errorMessage && <p className="error-message">{errorMessage}</p>}
            <header className="h">
                <div className="input-container">
                    <input
                        type="text"
                        value={searchTerm}
                        onChange={(e) => setSearchTerm(e.target.value)}
                        placeholder="Digite o nome do membro"
                        className="member-input"
                        onFocus={() => setIsFocused(true)}
                        ref={inputRef} // Referência para o input
                    />
                    {isFocused && filteredMembros.length > 0 && (
                        <ul className="suggestions-list" ref={suggestionsRef}>
                            {filteredMembros.map(membro => (
                                <li key={membro.id} onClick={() => handleSelectMember(membro)}>
                                    {membro.nome}
                                </li>
                            ))}
                        </ul>
                    )}
                </div>
                <h3>Inscritos</h3>
            </header>
            <ul className="registrationList">
                <li className="list-header">
                    <span>CPF</span>
                    <span>NOME</span>
                    <span>FUNÇÃO</span>
                    <span>STATUS</span>
                    <span>INSCRITO</span>
                </li>
                {!inscricoesError && !inscricoesLoading && inscricoes.map(inscricao => (
                    <li key={inscricao.id}>
                        <button type="button" onClick={() => handleDelete(inscricao.id)}>
                            {hasAccess && <img src={DeleteImg} alt="Deletar" />}
                        </button>
                        <span>{inscricao.membro?.cpf || "CPF não informado"}</span>
                        <span>{inscricao.membro?.nome || "Nome não informado"}</span>
                        <span>{inscricao.membro?.tipo ? normalizeUnderscore(inscricao.membro.tipo) : "Tipo não definido"}</span>
                        <span>{inscricao.statusParticipacao || "Status não informado"}</span>
                        <span>{inscricao.inscrito !== undefined ? (inscricao.inscrito ? "Sim" : "Não") : "Inscrição não definida"}</span>
                    </li>
                ))}
            </ul>
        </section>
    );
}