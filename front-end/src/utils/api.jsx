import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8080",
    headers: {
        "Content-Type": "application/json",
    },
});

api.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem("authToken");
        if (token) 
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

export const apiRequest = async (endpoint, method = "GET", body = null) => {
    try {
        const config = {
            method,
            url: endpoint,
        };

        if (method !== "GET" && body)
            config.data = body;

        const response = await api(config);
        return { data: response.data, error: null };
    } catch (error) {
        return { data: null, error: error.response?.data || error.message };
    }
};