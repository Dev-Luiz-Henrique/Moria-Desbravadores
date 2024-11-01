import { Link, useParams } from "react-router-dom";
import { Header } from "../components/layout/Header";
import { Footer } from "../components/layout/Footer";
import { ButtonGoBack } from "../components/layout/ButtonGoBack";
import { ViewMemberData } from "../components/screens/ViewMemberData";
import "./MemberView.css";

import AddIcon from "../assets/img/layout/plus.svg";
import SearchIcon from "../assets/img/layout/search.svg";

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
