import axios from "axios";

export const apiRequest = async (endpoint, method = "GET", body = null) => {
    const url = `http://localhost:8080${endpoint}`;

    try {
        const config = {
            method,
            url,
            headers: {
                "Content-Type": "application/json",
            },
            data: body,
        };

        const response = await axios(config);
        return { data: response.data, error: null };
    } catch (error) {
        return { data: null, error: error.response?.data || error.message };
    }
};