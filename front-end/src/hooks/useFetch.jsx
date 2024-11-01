import { useState, useEffect } from "react";
import { apiRequest } from "../utils/api"; 

export const useFetch = (endpoint, method = "GET", body = null, responseType = "json") => {
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(!!endpoint);
    const [error, setError] = useState(null);

    useEffect(() => {
        if (!endpoint) {
            setLoading(false);
            return;
        }

        const fetchData = async () => {
            const { data, error } = await apiRequest(endpoint, method, body, responseType);
            if (error) setError(error);
            else setData(data);
            setLoading(false);
        };

        fetchData();
    }, [endpoint, method, body, responseType]);

    return { data, setData, loading, error };
};