import { useState, useEffect } from "react";
import { useFetch } from "../../hooks/useFetch";
import { apiRequest } from "../../utils/api";
import { formatDateWithHours } from "../../utils/dateHelpers";
import { formatEndereco } from "../../utils/stringHelpers";

import ArrowRight from "../../assets/img/layout/arrow-right.svg";
import ArrowLeft from "../../assets/img/layout/arrow-left.svg";
import ImgEvent from "../../assets/img/default-event.png";
import BgPastEvents from "../../assets/img/backgrounds/bg-Mountain-02.svg";
import "./SectionEvents.css";

export function SectionEvents() {
    const { data, loading, error } = useFetch("/eventos", "GET");
    const [currentIndex, setCurrentIndex] = useState(0);
    const [imagesCache, setImagesCache] = useState({});

    useEffect(() => {
        const loadAllImages = async () => {
            if (data) {
                const newCache = { ...imagesCache };
                let updated = false;

                for (const event of data) {
                    if (!newCache[event.id]) {
                        try {
                            const { data: img } = await apiRequest(`/eventos/${event.id}/imagem`, "GET", null, "arraybuffer");
                            newCache[event.id] = img || ImgEvent;
                        } catch (error) {
                            console.error(`Erro ao carregar imagem do evento ${event.id}:`, error);
                            newCache[event.id] = ImgEvent;
                        }
                        updated = true;
                    }
                }

                if (updated)
                    setImagesCache(newCache);
            }
        };

        loadAllImages();
    }, [data]);

    const nextImage = () => {
        if (data && data.length > 0) 
            setCurrentIndex((prevIndex) => (prevIndex + 1) % data.length);
    };
    const prevImage = () => {
        if (data && data.length > 0)
            setCurrentIndex((prevIndex) => (prevIndex - 1 + data.length) % data.length);
    };
    

    const isDataAvailable = !error && data && data.length > 0;
    const currentEvent = isDataAvailable ? data[currentIndex] : {
        id: null,
        nome: "CAMPORI 2024",
        atracao: "Clube dos Desbravadores",
        dataInicio: new Date("2024-11-24"), 
        dataFim: new Date("2024-11-28"), 
        descricao: "Reunião anual do clube dos Desbravadores",
        logradouro: "Rua José Maria", 
        numero: "123",            
        endereco: {
            cep: "12345678",  
            cidade: "IABC Planalmira",
            estado: "GO",
            bairro: "Centro"
        }
    };
    if (loading) return <p>Loading...</p>;
    
    return (
        <div className="slider-container">
            <section className="slider">
                <img src={ArrowLeft} alt="Previous" className="arrow" onClick={prevImage} />

                <div className="event">
                    <img 
                        src={imagesCache[currentEvent.id] || ImgEvent} 
                        alt={currentEvent.nome} 
                    />
                    <div className="event-description">
                        <h2>{currentEvent.nome}</h2>
                        <span>
                            <p>
                                {formatEndereco(
                                    {
                                        bairro: currentEvent.endereco.bairro,
                                        cidade: currentEvent.endereco.cidade,
                                        estado: currentEvent.endereco.estado
                                    },
                                    currentEvent.logradouro,
                                    currentEvent.numero,
                                )}
                                {", no dia "}
                                {formatDateWithHours(currentEvent.dataInicio)}
                            </p>
                        </span>
                        <span className="description">
                            {currentEvent.descricao}
                        </span>
                    </div>
                </div>

                <img src={ArrowRight} alt="Next" className="arrow" onClick={nextImage} />
            </section>
            <img src={BgPastEvents} alt="Background" />
        </div>
    );
}