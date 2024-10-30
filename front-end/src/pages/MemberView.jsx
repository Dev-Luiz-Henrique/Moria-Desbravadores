import { Link } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import { useFetch } from "../hooks/useFetch";
import { apiRequest } from "../utils/api";
import { Authorities } from "../utils/authorities";
import { Header } from "../components/header";
import { Footer } from "../components/Footer";
import "./MemberView.css";
import { ViewMemberData } from "../components/ViewMemberData";
import AddIcon from "../assets/img/Plus.svg";
import SearchIcon from "../assets/img/Glass.svg";
import { ButtonGoBack } from "../components/ButtonGoBack";

export function MemberView() {
    return (
        <>
            <Header />
            <section className="container-manage-events-user-view">
                <div className="filter">
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
                </div>

                <div className="events-space">
                    <div className="btgb">
                        <ButtonGoBack />
                    </div>
                    <div className="events">
                        <ViewMemberData />
                        <ViewMemberData />
                        <ViewMemberData />
                    </div>
                </div>
            </section>
            <Footer />
        </>
    )
}