import { Footer } from "../components/Footer";
import { Header } from "../components/Header";
import { SectionEvents } from "../components/SectionEvents";
import { PastEvents } from "../components/PastEvents";
import { TalkToUs } from "../components/TalkToUs";

export function Landing() {
    return (
        <>
            <Header />
            <section id="events">
                <SectionEvents />
            </section>
            <section id="past-events">
                <PastEvents />
            </section>
            <section id="contact">
                <TalkToUs />
            </section>
            <Footer />
        </>
    );
}
