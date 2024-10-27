import React from "react";
import { Link } from "react-router-dom";
import './NotFound.css';

export function NotFound() {
    return (
        <div className="not-found-container">
            <h2>404 - Página não encontrada</h2>
            <p>Desculpe, a página que você está tentando acessar não existe.</p>
            <Link to="/">Voltar para a página inicial</Link>
        </div>
    );
}