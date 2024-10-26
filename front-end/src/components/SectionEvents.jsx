import { useState, useEffect } from "react";
import { useFetch } from "../hooks/useFetch";
import { apiRequest } from "../utils/api";

import ArrowRight from "../assets/img/arrow-right.svg";
import ArrowLeft from "../assets/img/arrow-left.svg";
import ImgEvent from "../assets/img/default-event.png";
import BgPastEvents from "../assets/img/Background_Mountain02.svg";
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
                        //console.log(`Carregando imagem do evento ${event.id}`);
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
        setCurrentIndex((prevIndex) => (prevIndex + 1) % data.length);
    };
    const prevImage = () => {
        setCurrentIndex((prevIndex) => (prevIndex - 1 + data.length) % data.length);
    };

    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error loading events.</p>;

    return (
        <div className="slider-container">
            <section className="slider">
                <img src={ArrowLeft} alt="Previous" className="arrow" onClick={prevImage} />

                <div className="event">
                    <img 
                        src={imagesCache[data[currentIndex]?.id] || ImgEvent} 
                        alt={data[currentIndex]?.nome || "Evento"} 
                    />
                    <div className="event-description">
                        <h2>{data[currentIndex]?.nome || "CAMPORI 2024"}</h2>
                        <span>
                            <strong>ENDEREÇO:</strong> 
                            {data[currentIndex]?.endereco?.cidade || "IABC Planalmira - GO"}
                        </span>
                        <span>
                            <strong>DATA:</strong> 
                            {data[currentIndex]?.dataInicio || "24/28 de Julho"}
                        </span>
                        <span>
                            {data[currentIndex]?.descricao || "Reunião anual do clube dos Desbravadores"}
                        </span>
                    </div>
                </div>

                <img src={ArrowRight} alt="Next" className="arrow" onClick={nextImage} />
            </section>
            <img src={BgPastEvents} alt="Background" />
        </div>
    );
}