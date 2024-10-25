import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import { apiRequest } from "../utils/api";
import { ButtonGoBack } from "./ButtonGoBack"
import "./LoginBody.css"

export function LoginBody() {
    const navigate = useNavigate();
    const { login } = useAuth();

    const [email, setEmail] = useState("daniel.silva@example.com");
    const [password, setPassword] = useState("daniel123");
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    const validateEmail = (email) => {
        const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return regex.test(email);
    };
    const validatePassword = (password) => {
        return password.length >= 6;
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        setError("");
        setLoading(true);

        if (!validateEmail(email)) {
            setError("Email inválido.");
            setLoading(false);
            return;
        }
        if (!validatePassword(password)) {
            setError("A senha deve ter pelo menos 6 caracteres.");
            setLoading(false);
            return;
        }

        const { data, error: fetchError } = await apiRequest("/login", "POST", { email, password });

        if (fetchError)
            setError("Erro no login: " + fetchError);
        else {
            //const { token } = data;
            await login(data);
            navigate("/membros");
        }

        setLoading(false);
    };

    return (
        <section className="container-login-body">
            <div className="ButtonGoBack">
                <ButtonGoBack />
            </div>
            <div className="credenciais">
                <h3>LOGIN</h3>
                {error && <p className="error-message">{error}</p>}

                <form onSubmit={handleSubmit}>
                    <div className="credenciais-input">
                        <label htmlFor="email">EMAIL:</label>
                        <input id="email" type="email" value={email} 
                            onChange={(e) => setEmail(e.target.value)} required />
                    </div>

                    <div className="credenciais-input">
                        <label htmlFor="password">SENHA:</label>
                        <input id="password" type="password" value={password}
                            onChange={(e) => setPassword(e.target.value)} required />
                    </div>

                    <a href=""><p>Esqueci minha senha</p></a>
                    <button type="submit" disabled={loading}>
                        {loading ? "Carregando..." : "LOGIN"}
                    </button>
                </form>
            </div>
        </section>
    )
}