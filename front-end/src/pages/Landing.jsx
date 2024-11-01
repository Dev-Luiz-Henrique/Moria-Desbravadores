import { Footer } from "../components/layout/Footer";
import { Header } from "../components/layout/Header";
import { SectionEvents } from "../components/screens/SectionEvents";
import { PastEvents } from "../components/screens/PastEvents";
import { TalkToUs } from "../components/screens/TalkToUs";

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
