import React from "react";
import "./Registrationlist.css";
import DeleteImg from "../assets/img/Delete.svg";
import { useAuth } from "../context/AuthContext";
import { useFetch } from "../hooks/useFetch";

// TODO: Dados devem ser carregados dinamicamente da API
const data = [
    { id: 1, cpf: "383997758-43", nome: "DANIEL LEITE PEREIRA", função: "VOLUNTÁRIO" },
    { id: 2, cpf: "687420981-73", nome: "LUIZ HENRIQUE DE SANTANA", função: "DESBRAVADOR" },
    { id: 3, cpf: "123456789-00", nome: "MARIA DA SILVA", função: "VOLUNTÁRIO" },
    { id: 4, cpf: "987654321-00", nome: "JOÃO SOUZA", função: "DESBRAVADOR" },
    { id: 5, cpf: "456789123-00", nome: "CARLA ALMEIDA", função: "VOLUNTÁRIO" },
    // ... outros dados
];

export function Registrationlist({id}) {
    const { authorities } = useAuth();
    const allowedAuthorities = ["DIRETOR_CLUBE", "DIRETOR_ASSOCIADO", "SECRETARIO"];
    const hasAccess = allowedAuthorities.some(auth => authorities.includes(auth));

    //const { data, loading, error } = useFetch("/inscritos");
    //const filteredData = data ? data.filter(inscrito => inscrito.evento.id === id) : [];

    const handleDelete = (id) => {
        // TODO: Chamada para API de exclusão pode ser adicionada aqui
        console.log(`Excluir inscrito com ID: ${id}`);
    };

    return (
        <section className="box-shadow">
            <header className="h">
                {hasAccess && <input type="text" placeholder="Adicionar inscritos" />}
                <h3>Inscritos</h3>
                {/* {hasAccess && (
                    <button className="salvar" type="button">
                        SALVAR
                    </button>
                )} */}
            </header>
            <ul className="registrationList">
                <li className="list-header">
                    <span>CPF</span>
                    <span>NOME</span>
                    <span>FUNÇÃO</span>
                </li>
                {data.map((inscrito) => (
                    <li key={inscrito.id}>
                        {/* {hasAccess && (
                            <button type="button" onClick={() => handleDelete(inscrito.id)}>
                                <img src={DeleteImg} alt="Deletar" />
                            </button>
                        )} */}
                        <span>{inscrito.cpf}</span>
                        <span>{inscrito.nome}</span>
                        <span>{inscrito.função}</span>
                    </li>
                ))}
            </ul>
        </section>
    );
}