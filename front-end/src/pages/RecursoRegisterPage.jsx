import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { apiRequest } from "../utils/api";
import { ButtonGoBack } from "../components/ButtonGoBack";
import { Footer } from "../components/Footer";
import { Header } from "../components/header"; // Corrigi o nome do Header para maiúsculo para consistência
import { RecursoSignUp } from "../components/RecursoDataSignUp"; // Formulário de recursos
import "./RecursoRegisterPage.css"; // Estilo da página específica de recursos

export function RecursoRegisterPage() {
    const { id } = useParams();
    const [recursoData, setRecursoData] = useState(null); // Alterado para recursoData
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchRecurso = async () => {
            if (id) {
                const response = await apiRequest(`/recursos/${id}`, "GET"); // Endpoint para buscar o recurso

                if (response.error) {
                    setError(response.error);
                    console.error("Erro ao buscar recurso:", response.error);
                } 
                else setRecursoData(response.data); // Armazena os dados do recurso
            } 
            else 
                setRecursoData(null); // Se não houver ID, significa que é um cadastro novo
            setLoading(false);
        };

        fetchRecurso();
    }, [id]);

    if (loading) return <p>Carregando...</p>;
    if (error) return <p>Erro: {error}</p>;

    return (
        <>
            <Header />
            <div className="container-register-page">
                <div className="return-btn">
                    <ButtonGoBack />
                </div>
                <RecursoSignUp initialData={recursoData} /> {/* Passa os dados do recurso para o formulário */}
            </div>
            <Footer />
        </>
    );
}