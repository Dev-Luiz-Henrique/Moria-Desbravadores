import { PastEvents } from "../components/PastEvents";
import AddIcon from "../assets/img/Plus.svg";
import SearchIcon from "../assets/img/Glass.svg";
import "./ManageEvents.css"
import { CardEvents } from "../components/CardEvents";
import { Header } from "../components/header";
import { Footer } from "../components/Footer";

export function ManageEvents() {
    return (
        <>
            <Header />
            <section className="container-manage-events-page">
                <div className="filter">
                    <div className="detail">
                        <button>
                            <img src={AddIcon} alt="AddIcon" />
                            <p>CRIAR EVENTO</p>
                        </button>
                        <div className="search">
                            <img src={SearchIcon} alt="SearchIcon" />
                            <input type="text" />
                        </div>
                    </div>
                </div>

                <div className="events-space">
                    <div className="events">
                        <CardEvents />
                        <CardEvents />
                        <CardEvents />
                    </div>
                </div>
            </section>
            <Footer />
        </>
    )
}