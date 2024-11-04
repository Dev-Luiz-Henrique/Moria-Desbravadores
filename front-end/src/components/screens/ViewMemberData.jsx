import React from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../context/AuthContext";
import { useFetch } from "../../hooks/useFetch";
import { Authorities } from "../../utils/authorities";
import { formatEndereco, normalizeUnderscore } from "../../utils/stringHelpers";
import { formatDate } from "../../utils/dateHelpers";
import { apiRequest } from "../../utils/api";

import DeleteIcon from "../../assets/img/layout/delete.svg";
import EditIcon from "../../assets/img/layout/edit.svg";
import ProfileLogo from "../../assets/img/layout/user-card.svg";
import "./ViewMemberData.css";

export function ViewMemberData({ id }) {
    const navigate = useNavigate();
    const { authorities, membro: authMembro } = useAuth();
    const allowedAuthorities = [Authorities.DIRETOR_CLUBE, Authorities.DIRETOR_ASSOCIADO, Authorities.SECRETARIO];
    const hasAccess = allowedAuthorities.some(auth => authorities.includes(auth));

    const { data: memberFetched, loading, error } = useFetch(id ? `/membros/${id}` : null, "GET");
    const membro = id ? memberFetched : authMembro;

    if (loading) return <p>Carregando dados do membro...</p>;
    if (error) return <p>Erro ao carregar dados do membro.</p>;
    if (!membro) return <p>Dados do membro não disponíveis.</p>;

    const handleEdit = () => {
        if (!hasAccess) {
            alert("Você não tem permissão para editar membros.");
            return;
        }
        navigate(`/cadastrar-membro/${membro.id}`);
    };

    const handleDelete = async () => {
        if (!hasAccess) {
            alert("Você não tem permissão para excluir membros.");
            return;
        }

        const confirmed = window.confirm("Você tem certeza que deseja excluir este membro?");
        if (confirmed) {
            const { error: deleteError } = await apiRequest(`/membros/${id}`, "DELETE");
            if (deleteError) console.error(deleteError);
            else navigate("/gerenciar-membros");
        }
    };

    const formatSexo = (sexo) => (sexo === "M" ? "Masculino" : sexo === "F" ? "Feminino" : "Outro");

    return (
        <div className="member-view-container">
            {hasAccess && (
                <div className="action-buttons">
                    <button onClick={handleEdit}><img src={EditIcon} alt="Editar" /></button>
                    <button onClick={handleDelete}><img src={DeleteIcon} alt="Deletar" /></button>
                </div>
            )}
            <div className="member-header">
                <img src={ProfileLogo} alt="Perfil" className="profile-img" />
                <h2 className="member-name">{membro?.nome || "Nome do Membro"}</h2>
            </div>
            <div className="member-details">
                {[
                    { label: "FUNÇÃO:", value: membro.tipo ? normalizeUnderscore(membro.tipo) : "Tipo não informado" },
                    { label: "STATUS:", value: membro?.ativo !== undefined ? (membro.ativo ? "Ativo" : "Inativo") : "Status não informado" },
                    { label: "SEXO:", value: membro.sexo ? formatSexo(membro.sexo) : "Sexo não informado" },
                    { label: "DATA DE NASCIMENTO:", value: membro.dataNascimento ? formatDate(membro.dataNascimento) : "Data não informada" },
                    { label: "ENDEREÇO:", value: membro.endereco ? formatEndereco(membro.endereco, membro.logradouro, membro.numero) : "Endereço não informado" },
                    { label: "EMAIL:", value: membro?.email || "Email não informado" },
                    { label: "CELULAR:", value: membro?.celular || "Celular não informado" },
                    { label: "TELEFONE:", value: membro?.telefone || "Telefone não informado" },
                    { label: "CPF:", value: membro?.cpf || "CPF não informado" },
                    { label: "RG:", value: membro?.rg || "RG não informado" },
                    { label: "ÓRGÃO EXPEDIDOR:", value: membro?.orgaoExpedidor || "Órgão não informado" },
                    { label: "TAMANHO DA CAMISA:", value: membro?.tamanhoCamisa || "Tamanho não informado" },
                    { label: "ESTADO CIVIL:", value: membro?.estadoCivil || "Estado civil não informado" },
                    { label: "BATIZADO:", value: membro?.batizado ? "Sim" : "Não" }
                ].map((item, index) => (
                    <div className="member-detail" key={index}>
                        <span className="detail-label">{item.label}</span>
                        <span className="detail-value">{item.value}</span>
                    </div>
                ))}
            </div>
        </div>
    );
}