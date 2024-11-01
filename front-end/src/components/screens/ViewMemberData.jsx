import React from "react";
import { useNavigate } from 'react-router-dom';
import { useAuth } from "../../context/AuthContext";
import { useFetch } from "../../hooks/useFetch";
import { Authorities } from "../../utils/authorities";
import { formatEndereco, normalizeUnderscore } from "../../utils/stringHelpers";
import { formatDate } from "../../utils/dateHelpers";
import { apiRequest } from "../../utils/api";
import "./ViewMemberData.css";

import Delete from "../../assets/img/layout/delete.svg";
import Edit from "../../assets/img/layout/edit.svg";
import ProfileLogo from "../../assets/img/layout/user-card.svg";

export function ViewMemberData({ id }) {
    const navigate = useNavigate();
    const { authorities, membro: authMembro } = useAuth();
    const allowedAuthorities = [Authorities.DIRETOR_CLUBE, Authorities.DIRETOR_ASSOCIADO, Authorities.SECRETARIO];
    const hasAccess = allowedAuthorities.some(auth => authorities.includes(auth));

    const { data: memberFetched, loading, error } = useFetch(id ? `/membros/${id}` : null, "GET");
    const membro = id ? memberFetched : authMembro;

    if (loading) return <p>Carregando dados do membro...</p>;
    if (error) return <p>Erro ao carregar dados do membro.</p>;


    if (loading) return <p>Carregando dados do membro...</p>;
    if (error) return <p>Erro ao carregar dados do membro.</p>;

    const handleEdit = () => {
        if (!hasAccess) {
            alert("Você não tem permissão para editar membros.");
            return;
        }
        navigate(`/cadastrar-membro/${id}`);
    };

    const handleDelete = async () => {
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
                navigate("/gerenciar-membros");
        }
    };

    const formatSexo = (sexo) => sexo === "M" ? "Masculino" : sexo === "F" ? "Feminino" : "Outro";

    return (
        <div className="profile-card-member-view">
            {hasAccess && (
                <div className="profile-card-buttons">
                    <button onClick={handleEdit}><img src={Edit} alt="Editar" /></button>
                    <button onClick={handleDelete}><img src={Delete} alt="Deletar" /></button>
                </div>
            )}
            
            <div className="profile-card-header">
                <img src={ProfileLogo} alt="Perfil" />
                <h4>{membro?.nome || "Nome do Membro"}</h4>
            </div>

            <span>
                <b>TIPO:</b>
                <p>{membro.tipo ? normalizeUnderscore(membro.tipo) : "Tipo não informado"}</p>
            </span>
            <span>
                <b>STATUS:</b>
                <p>{membro?.ativo !== undefined ? (membro.ativo ? "Sim" : "Não") : "Status não informado"}</p>
            </span>
            <span>
                <b>SEXO:</b>
                <p>{membro.sexo ? formatSexo(membro.sexo) : "Sexo não informado"}</p>
            </span>
            <span>
                <b>DATA DE NASCIMENTO:</b>
                <p>{membro.dataNascimento ? formatDate(membro.dataNascimento) :  "Data não informada"}</p>
            </span>
            <span>
                <b>ENDEREÇO:</b>
                <p>
                    {membro.endereco ? formatEndereco(membro.endereco, membro.logradouro, membro.numero) 
                        : "Endereço não informado"}
                </p>
            </span>
            <span><b>EMAIL:</b><p>{membro?.email || "Email não informado"}</p></span>
            <span><b>CELULAR:</b><p>{membro?.celular || "Celular não informado"}</p></span>
            <span><b>TELEFONE:</b><p>{membro?.telefone || "Telefone não informado"}</p></span>
            <span><b>CPF:</b><p>{membro?.cpf || "CPF não informado"}</p></span>
            <span><b>RG:</b><p>{membro?.rg || "RG não informado"}</p></span>
            <span><b>ÓRGÃO EXPEDIDOR:</b><p>{membro?.orgaoExpedidor || "Órgão não informado"}</p></span>
            <span><b>TAMANHO DA CAMISA:</b><p>{membro?.tamanhoCamisa || "Tamanho não informado"}</p></span>
            <span><b>ESTADO CIVIL:</b><p>{membro?.estadoCivil || "Estado civil não informado"}</p></span>
            <span><b>BATIZADO:</b><p>{membro?.batizado ? "Sim" : "Não"}</p></span>
            {/* <span>
                <b>FICHA DE SAÚDE:</b>
                <p><a href={membro?.fichaSaude || "#"} download>ficha_saude.png</a></p>
            </span> */}
        </div>
    );
}