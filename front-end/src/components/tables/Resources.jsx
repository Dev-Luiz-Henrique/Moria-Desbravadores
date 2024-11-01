import { Link, useNavigate } from 'react-router-dom';
import React from "react";
import "./Resources.css";

import DeleteImg from "../../assets/img/layout/delete.svg";
import PlusImg from "../../assets/img/layout/plus.svg";

const data = [
    {
        nome: "cadeira",
        valor: "50,00",
        quantidade: "250",
        categoria: "móvel",
        descrição: "cadeira de plástico",
    },
    {
        nome: "copo",
        valor: "5,00",
        quantidade: "50",
        categoria: "descartável",
        descrição: "conjunto de copos de plástico",
    },
];

export function Resources() {
    const navigate = useNavigate();
    const handleCreate = () => {
        navigate("/cadastrar-recurso");
    };

    return (
        <section className='box-shadow'>
            <header className='resources-header'>
                {/* TODO: Esse Botão só será exibido para quem tiver a autorização adequada */}
                <button onClick={handleCreate} className='adicionar' type='button'>
                    <img src={PlusImg} alt='' />
                    ADICIONAR RECURSO
                </button>
                <h3>Recursos</h3>
                {/* TODO: Esse Botão só será exibido para quem tiver a autorização adequada */}
                {/* <button className='salvar' type='button'>
                    SALVAR
                </button> */}
            </header>
            <ul className='resourcesList'>
                <li className='list-header'>
                    <span>NOME</span>
                    <span>VALOR</span>
                    <span>QUANTIDADE</span>
                    <span>CATEGORIA</span>
                    <span>DESCRIÇÃO</span>
                </li>
                {data.map((inscrito) => {
                    return (
                        // Se esse objeto tiver uma ID, será uma key melhor
                        <li key={inscrito.nome}>
                            {/* TODO: Esse Botão só será exibido para quem tiver a autorização adequada */}
                            <button type='button'>
                                {/* <img src={DeleteImg} alt='Deletar' /> */}
                            </button>
                            <span>{inscrito.nome}</span>
                            <span>R$ {inscrito.valor}</span>
                            <span>{inscrito.quantidade} un</span>
                            <span>{inscrito.categoria}</span>
                            <span>{inscrito.descrição}</span>
                        </li>
                    );
                })}
            </ul>
        </section>
    );
}
