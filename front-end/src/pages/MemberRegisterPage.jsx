import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { apiRequest } from "../utils/api";
import { ButtonGoBack } from "../components/ButtonGoBack";
import { Footer } from "../components/Footer";
import { Header } from "../components/header";
import { MemberDataSignUp } from "../components/MemberDataSignUp";
import "./MemberRegisterPage.css";

export function MemberRegisterPage() {
    const { id } = useParams();
    const [memberData, setMemberData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchMember = async () => {
            if (id) {
                const response = await apiRequest(`/membros/${id}`, "GET");

                if (response.error) {
                    setError(response.error);
                    console.error("Erro ao buscar membro:", response.error);
                } 
                else setMemberData(response.data);
            } 
            setLoading(false);
        };

        fetchMember();
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
                {memberData && <MemberDataSignUp initialData={memberData} />}
            </div>
            <Footer />
        </>
    );
}