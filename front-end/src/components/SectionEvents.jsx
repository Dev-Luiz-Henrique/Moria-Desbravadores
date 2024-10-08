// import EventImage from "../assets/img/"
import ArrowRight from "../assets/img/arrow-right.svg"
import ArrowLeft from "../assets/img/arrow-left.svg"
import ImgEvent from "../assets/img/default-event.png"
import BgPastEvents from "../assets/img/Background_Mountain02.svg"
import "./SectionEvents.css"

export function SectionEvents() {
    return (
        <div className="slider-container">
            <section className="slider">
                <img src={ArrowLeft} alt="" className="arrow" />
                <div className="event">
                    <img src={ImgEvent} alt="" />
                    <div className="event-description">
                        <h2>CAMPORI 2024</h2>
                        <span><strong>ENDEREÇO:</strong>IABC Planalmira - GO</span>
                        <span><strong>DATA:</strong>24/28 de Julho</span>
                        <span>Reunião anual do clube dos Desbravadores</span>

                        <button className="event-subscribe-no-login">INSCREVA-SE</button>
                    </div>
                </div>
                <img src={ArrowRight} alt="" className="arrow" />
            </section>
            <img src={BgPastEvents} alt="" />
        </div>
    )
}
