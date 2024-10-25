import { Navigate } from "react-router-dom";

export function PrivateRoute({ children }) {
    const token = localStorage.getItem("authToken");    console.log("token:" + token)
    
    return token ? children : <Navigate to="/login" />;
}