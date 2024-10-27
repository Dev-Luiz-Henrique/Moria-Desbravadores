import React from 'react';
import { Navigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

export function PrivateRoute({ children, allowedAuthorities }) {
    const { authorities, isLoading } = useAuth();

    if (isLoading) return null;
    const hasAccess = allowedAuthorities.some(auth => authorities.includes(auth));
    
    if (!hasAccess) {
        alert("Você não tem permissão para acessar esta página.");
        return <Navigate to="/" />; 
    }
    return children;
}