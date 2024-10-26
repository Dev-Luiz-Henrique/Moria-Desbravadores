import React, { createContext, useState, useContext } from "react";
import { jwtDecode } from "jwt-decode";
import { apiRequest } from "../utils/api";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [membro, setMembro] = useState(null);
    const [token, setToken] = useState(null);
    const [authorities, setAuthorities] = useState([]);

    const login = async (authToken) => {
        setToken(authToken);
        const decodedToken = jwtDecode(authToken);
    
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
        <AuthContext.Provider value={{ membro, token, authorities, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => {
    return useContext(AuthContext);
};