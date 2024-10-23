import { useState, useEffect } from "react";
import axios from "axios";

export const useFetch = (endpoint, method = "GET", body = null) => {
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const url = `http://localhost:8080${endpoint}`;
                let response;

                switch (method) {
                    case "GET":
                        response = await axios.get(url);
                        break;
                    case "POST":
                        response = await axios.post(url, body);
                        break;
                    case "PUT":
                        response = await axios.put(url, body);
                        break;
                    case "DELETE":
                        response = await axios.delete(url);
                        break;
                    default:
                        throw new Error(`Método HTTP não suportado: ${method}`);
                }

                setData(response.data);
            } catch (err) {
                setError(err);
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, [endpoint, method, body]);

    return { data, loading, error };
};