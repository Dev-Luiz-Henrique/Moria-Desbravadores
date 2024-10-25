import axios from "axios";
import { getToken } from "./auth";

const api = axios.create({
    baseURL: "http://localhost:8080",
    headers: {
        "Content-Type": "application/json",
    },
});

api.interceptors.request.use(
    (config) => {
        const token = getToken();
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
        if (error.response.status === 401) {
            localStorage.removeItem("authToken");
            window.location.href = "/login";
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