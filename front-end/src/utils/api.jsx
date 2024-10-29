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

        if (error.response && error.response.status === 401) {
            localStorage.removeItem("authToken");

            if (originalRequest.url !== "/login" && window.location.pathname !== "/login") {
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
        let errorMessage;

        if (error.response) {
            // Checa se há uma mensagem específica retornada pela API e utiliza ela
            errorMessage = error.response.data?.message || "";
            
            if (!errorMessage) {
                switch (error.response.status) {
                    case 400:
                        errorMessage = "Requisição inválida. Verifique os dados enviados.";
                        break;
                    case 401:
                        errorMessage = "Não autorizado. Por favor, faça login.";
                        break;
                    case 403:
                        errorMessage = "Acesso negado. Você não tem permissão para esta ação.";
                        break;
                    case 404:
                        errorMessage = "Recurso não encontrado. Verifique o endereço e tente novamente.";
                        break;
                    case 500:
                        errorMessage = "Erro interno no servidor. Tente novamente mais tarde.";
                        break;
                    case 503:
                        errorMessage = "Servidor indisponível no momento. Tente novamente em alguns minutos.";
                        break;
                    default:
                        errorMessage = `Erro inesperado. Código: ${error.response.status}`;
                }
            }
        } 
        else if (error.request)
            errorMessage = "Erro de conexão. Verifique sua conexão com a internet.";
        else
            errorMessage = "Erro inesperado. Tente novamente mais tarde.";
        
        if (import.meta.env.VITE_ENVIRONMENT === "development")
            console.error("Erro de requisição:", error);

        return { data: null, error: errorMessage };
    }
};