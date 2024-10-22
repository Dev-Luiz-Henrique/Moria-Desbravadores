import ArrowBack from "../assets/img/arrow-back.svg"
import "./ButtonGoBack.css"

export function ButtonGoBack() {
    return(
        <button className="button-go-back">
            <img src={ArrowBack} alt="" />
            <p>VOLTAR</p>
        </button>
    )
}