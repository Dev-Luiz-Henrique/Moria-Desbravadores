import { Footer } from "../components/Footer";
import { Header } from "../components/header";
import { SectionEvents } from "../components/SectionEvents";
import { PastEvents } from "../components/PastEvents";

export function Landing() {
    return (
        <>
            <Header />
            <SectionEvents />
            <PastEvents />
            <Footer />
        </>
    )
}