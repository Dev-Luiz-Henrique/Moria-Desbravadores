import React from 'react';
import { Navigate } from 'react-router-dom';
import { getAuthoritiesFromToken, getToken } from '../utils/auth';

export function PrivateRoute({ children, allowedAuthorities }) {
    const userAuthorities = getAuthoritiesFromToken();
    const hasAccess = allowedAuthorities.some(auth => userAuthorities.includes(auth));

    if (!hasAccess) {
        alert("Você não tem permissão para acessar esta página. " + getToken());
        console.log(getAuthoritiesFromToken())
        return <Navigate to="/" />; 
    }
    return children;
}