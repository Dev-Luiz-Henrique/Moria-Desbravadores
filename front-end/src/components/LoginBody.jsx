import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { ButtonGoBack } from "./ButtonGoBack"
import "./LoginBody.css"

export function LoginBody() {
    const navigate = useNavigate();
    
    const [email, setEmail] = useState("daniel.silva@example.com");
    const [password, setPassword] = useState("daniel123");
    const [error, setError] = useState("");

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

        if (!validateEmail(email)) {
            setError("Email inválido.");
            return;
        }
        if (!validatePassword(password)) {
            setError("A senha deve ter pelo menos 6 caracteres.");
            return;
        }

        try {
            const response = await fetch("http://localhost:8080/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ email, password }),
            });

            const data = await response.text();
            if (response.ok) {
                localStorage.setItem("authToken", data); 
                console.log("Login bem-sucedido:", data);
                navigate("/membros");
            } else {
                setError("Erro no login: " + data);
                console.error("Erro no login:", data);
            }
        } catch (error) {
            setError("Erro na requisição.");
            console.error("Erro na requisição:", error);
        }
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
                    <button type="submit">LOGIN</button>
                </form>
            </div>
        </section>
    )
}