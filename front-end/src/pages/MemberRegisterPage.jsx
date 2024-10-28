import { ButtonGoBack } from "../components/ButtonGoBack";
import { Footer } from "../components/Footer";
import { Header } from "../components/Header";
import { MemberDataSignUp } from "../components/MemberDataSignUp";
import "./MemberRegisterPage.css"

export function MemberRegisterPage() {
    return (
        <>
            <Header />
            <div className="container-register-page">
                <div className="return-btn"><ButtonGoBack /></div>
                <MemberDataSignUp />
            </div>
            <Footer />
        </>
    )
}