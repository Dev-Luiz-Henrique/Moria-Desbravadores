import EventOne from "../assets/img/evento01.png"
import EventTwo from "../assets/img/evento02.png"
import EventThree from "../assets/img/evento03.png"
import EventFour from "../assets/img/evento04.png"
// import BgPastEvents from "../assets/img/Background_Mountain02.svg"
import "./PastEvents.css"

export function PastEvents() {
    return(
        <section className="past-events-container">
            <h2>EVENTOS PASSADOS</h2>
            <div className="past-events-cards">
                <div className="past-events-card">
                    <img src={EventOne} alt="" />
                    <span>
                        <h3>Evento 1</h3>
                        <p>02/04/2024</p>
                    </span>
                </div>

                <div className="past-events-card">
                    <img src={EventTwo} alt="" />
                    <span>
                        <h3>Evento 2</h3>
                        <p>02/04/2024</p>
                    </span>
                </div>

                <div className="past-events-card">
                    <img src={EventThree} alt="" />
                    <span>
                        <h3>Evento 3</h3>
                        <p>02/04/2024</p>
                    </span>
                </div>

                <div className="past-events-card">
                    <img src={EventFour} alt="" />
                    <span>
                        <h3>Evento 4</h3>
                        <p>02/04/2024</p>
                    </span>
                </div>
            </div>
        </section>
    )
}