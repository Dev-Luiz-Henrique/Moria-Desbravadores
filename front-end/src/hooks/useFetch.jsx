import { useState, useEffect } from "react";
import { apiRequest } from "../utils/api"; 

export const useFetch = (endpoint, method = "GET", body = null) => {
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            const { data, error } = await apiRequest(endpoint, method, body);

            if (error) setError(error);
            else setData(data);

            setLoading(false);
        };

        fetchData();
    }, [endpoint, method, body]);

    return { data, setData, loading, error };
};