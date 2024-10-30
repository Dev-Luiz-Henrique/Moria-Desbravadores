import { Link } from "react-router-dom";
import { Header } from "../components/Header";
import { Footer } from "../components/Footer";
import "./MemberView.css";
import { ViewMemberData } from "../components/ViewMemberData";
import AddIcon from "../assets/img/Plus.svg";
import SearchIcon from "../assets/img/Glass.svg";
import { ButtonGoBack } from "../components/ButtonGoBack";
import { useParams } from "react-router-dom";

export function MemberView() {
    const { id } = useParams();
    
    return (
        <>
            <Header />
            <section className="container-manage-events-user-view">
                {/* <div className="filter">
                    <div className="detail">
                        <button>
                            <img src={AddIcon} alt="AddIcon" />
                            <p>CADASTRAR MEMBRO</p>
                        </button>
                        <div className="search">
                            <img src={SearchIcon} alt="SearchIcon" />
                            <input type="text" />
                        </div>
                    </div>
                </div> */}

                <div className="events-space">
                    <div className="btgb">
                        <ButtonGoBack />
                    </div>
                    <div className="events">
                        <ViewMemberData id={id} />
                    </div>
                </div>
            </section>
            <Footer />
        </>
    );
}
