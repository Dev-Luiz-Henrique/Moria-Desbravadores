import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../context/AuthContext";
import { apiRequest } from "../../utils/api";
import { ButtonGoBack } from "../layout/ButtonGoBack"
import "./LoginBody.css";

export function LoginBody() {
    const navigate = useNavigate();
    const { login } = useAuth();

    const [email, setEmail] = useState("charlinho@hotmail.com");
    const [password, setPassword] = useState("#MinhaSenhaSuperSecreta");
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
    
        const trimmedEmail = email.trim();
        const trimmedPassword = password.trim();
    
        setEmail(trimmedEmail);
        setPassword(trimmedPassword);
    
        if (!validateEmail(trimmedEmail)) {
            setError("Email inválido.");
            setLoading(false);
            return;
        }
        if (!validatePassword(trimmedPassword)) {
            setError("A senha deve ter pelo menos 6 caracteres.");
            setLoading(false);
            return;
        }
    
        const { data, error: fetchError } = await apiRequest("/login", "POST", { email: trimmedEmail, password: trimmedPassword });
    
        if (fetchError) {
            setError("Erro no login: " + fetchError);
        } else {
            await login(data);
            navigate("/membro");
            window.location.reload();
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

                    {/* <a href=""><p>Esqueci minha senha</p></a> */}
                    <button type="submit" disabled={loading}>
                        {loading ? "Carregando..." : "LOGIN"}
                    </button>

                    {error && <p className="error-message">{error}</p>}
                </form>

                
            </div>
        </section>
    )
}