import { Footer } from "../components/Footer";
import { Header } from "../components/header";
import { SectionEvents } from "../components/SectionEvents";
import { PastEvents } from "../components/PastEvents";
import { TalkToUs } from "../components/TalkToUs";

export function Landing() {
    return (
        <>
            <Header />
            <SectionEvents />
            <PastEvents />
            <TalkToUs />
            <Footer />
        </>
    )
}