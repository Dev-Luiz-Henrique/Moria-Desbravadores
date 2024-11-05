import React from "react";
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from "../../context/AuthContext";
import { useFetch } from "../../hooks/useFetch";
import { apiRequest } from "../../utils/api";
import "./Resources.css";

import DeleteImg from "../../assets/img/layout/delete.svg";
import PlusImg from "../../assets/img/layout/plus.svg";

export function Resources({eventoId}) {
    const navigate = useNavigate();
    const { authorities } = useAuth();
    const allowedAuthorities = ["DIRETOR_CLUBE", "DIRETOR_ASSOCIADO", "SECRETARIO", "TESOUREIRO"];
    const hasAccess = allowedAuthorities.some(auth => authorities.includes(auth));

    const { data: recursos, setData: setRecursos, loading, error } = useFetch(`/recursos/evento/${eventoId}`, "GET");

    const handleCreate = () => {
        if (!hasAccess) {
            alert("Você não tem permissão para cadastrar recursos.");
            return;
        }
        navigate(`/cadastrar-recurso/${eventoId}`);
    };

    const handleDelete = async (id) => {
        if (!hasAccess) {
            alert("Você não tem permissão para excluir recursos.");
            return;
        }

        const confirmed = window.confirm("Você tem certeza que deseja excluir este recurso?");
        if (confirmed) {
            const { error: deleteError } = await apiRequest(`/recursos/${id}`, "DELETE");

            if (deleteError)
                console.error(deleteError);
            else 
                setRecursos((prevRecursos) => prevRecursos.filter(recurso => recurso.id !== id));
        }
    };

    return (
        <section className='box-shadow'>
            <header className='resources-header'>
                <button onClick={handleCreate} className='adicionar' type='button'>
                    <img src={PlusImg} alt='' />
                    <span>ADICIONAR RECURSO</span>
                </button>
                <h3>Recursos</h3>
            </header>
            <ul className='resourcesList'>
                <li className='list-header'>
                    <span>NOME</span>
                    <span>VALOR</span>
                    <span>QUANTIDADE</span>
                    <span>CATEGORIA</span>
                    <span>DESCRIÇÃO</span>
                </li>
                {!error && !loading && recursos.map((recurso) => {
                    return (
                        <li key={recurso.id}>
                            <button type='button' onClick={() => handleDelete(recurso.id)}>
                                {hasAccess && <img src={DeleteImg} alt='Deletar'/>}
                            </button>
                            <span>{recurso.nome}</span>
                            <span>R$ {recurso.valor}</span>
                            <span>{recurso.quantidade} un</span>
                            <span>{recurso.categoria}</span>
                            <span>{recurso.descricao}</span>
                        </li>
                    );
                })}
            </ul>
        </section>
    );
}