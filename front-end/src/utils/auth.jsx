import { jwtDecode } from "jwt-decode";

const getToken = () => localStorage.getItem("authToken");

const getAuthoritiesFromToken = () => {
    const token = getToken();
    if (token) {
        const decodedToken = jwtDecode(token);
        console.log(decodedToken)
        return decodedToken.authorities.map(auth => auth.authority) || [];
    }
    return [];
};

export { getToken, getAuthoritiesFromToken };