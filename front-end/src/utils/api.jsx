import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8080",
    headers: {
        "Content-Type": "application/json",
    },
});

const publicRoutes = {
    "/login": ["POST"],
    "/eventos": ["GET"],
};

api.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem("authToken");

        const isPublicRoute = Object.keys(publicRoutes).some((route) => {
            return config.url.startsWith(route) && publicRoutes[route].includes(config.method.toUpperCase());
        });

        if (token && !isPublicRoute)
            config.headers.Authorization = `Bearer ${token}`;
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

api.interceptors.response.use(
    (response) => {
        return response;
    },
    (error) => {
        const originalRequest = error.config;

        if (error.response.status === 401) {
            localStorage.removeItem("authToken");

            if (originalRequest.url !== "/login") {
                window.location.href = "/login";
                alert("Sessão expirada. Por favor, faça login novamente.");
            } 
        }
        return Promise.reject(error);
    }
);

export const apiRequest = async (endpoint, method = "GET", body = null, responseType = "json") => {
    try {
        const config = {
            method,
            url: endpoint,
            responseType
        };

        if (method !== "GET" && body)
            config.data = body;

        const response = await api(config);

        if (responseType === "arraybuffer") {
            const base64String = btoa(new Uint8Array(response.data).reduce((acc, byte) => acc + String.fromCharCode(byte),""));
            return { data: `data:image/png;base64,${base64String}`, error: null };
        }

        return { data: response.data, error: null };
    } catch (error) {
        return { data: null, error: error.response?.data || error.message };
    }
};