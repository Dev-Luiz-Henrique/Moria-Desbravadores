import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { apiRequest } from "../utils/api";
import { ButtonGoBack } from "../components/ButtonGoBack";
import { Footer } from "../components/Footer";
import { Header } from "../components/header";
import { EventoDataSignUp } from "../components/EventoDataSignUp";
import "./EventoRegisterPage.css";

export function EventoRegisterPage() {
    const { id } = useParams();
    const [eventoData, setEventoData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchEvento = async () => {
            if (id) {
                const response = await apiRequest(`/eventos/${id}`, "GET");

                if (response.error) {
                    setError(response.error);
                    console.error("Erro ao buscar evento:", response.error);
                }
                else setEventoData(response.data);
            } 
            else 
                setEventoData({});
            setLoading(false);
        };

        fetchEvento();
    }, [id]);

    if (loading) return <p>Carregando...</p>;
    if (error) return <p>Erro: {error}</p>;

    return (
        <>
            <Header />
            <div className="container-register-evento-page">
                <div className="return-btn">
                    <ButtonGoBack />
                </div>
                <EventoDataSignUp initialData={eventoData}/>
            </div>
            <Footer />
        </>
    );
}