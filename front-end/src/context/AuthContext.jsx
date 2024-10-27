import React, { createContext, useState, useContext, useEffect } from "react";
import { jwtDecode } from "jwt-decode";
import { apiRequest } from "../utils/api";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [membro, setMembro] = useState(null);
    const [token, setToken] = useState(null);
    const [authorities, setAuthorities] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const authToken = localStorage.getItem("authToken");
        if (authToken) {
            const decodedToken = jwtDecode(authToken);
            if (decodedToken.exp * 1000 < Date.now()) {
                logout();
                alert("Token expirado. Por favor, faça login novamente.");
            } else if (window.location.pathname !== "/login") {
                login(authToken);
            }
        }
        setIsLoading(false);
    }, []);


    const login = async (authToken) => {
        setToken(authToken);
        const decodedToken = jwtDecode(authToken);

        if (decodedToken.exp * 1000 < Date.now()) {
            alert("Token expirado. Por favor, faça login novamente.");
            logout(); return;
        }        
    
        const authoritiesFromToken = decodedToken.authorities
            ? decodedToken.authorities.map(auth => auth.authority) : [];
        setAuthorities(authoritiesFromToken);
    
        const email = decodedToken.sub;
        const { data: membroData } = await apiRequest(`/membros/email?email=${email}`, "GET");
        setMembro(membroData);

        localStorage.setItem("authToken", authToken);
    };
    
    const logout = () => {
        setMembro(null);
        setToken(null);
        setAuthorities([]);
        localStorage.removeItem("authToken");
    };

    return (
        <AuthContext.Provider value={{ membro, token, authorities, login, logout, isLoading }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => {
    return useContext(AuthContext);
};