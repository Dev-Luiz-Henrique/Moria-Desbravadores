import { useNavigate } from "react-router-dom";
import ArrowBack from "../assets/img/arrow-back.svg";
import "./ButtonGoBack.css";

export function ButtonGoBack() {
    const navigate = useNavigate();

    const handleGoBack = () => {
        navigate(-1); // Navega para página anterior
    };

    return (
        <button className="button-go-back" onClick={handleGoBack}>
            <img src={ArrowBack} alt="Voltar" />
            <p>VOLTAR</p>
        </button>
    );
}