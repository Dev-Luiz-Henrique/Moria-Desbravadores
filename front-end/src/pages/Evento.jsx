import { Header } from '../components/header';
import { ButtonGoBack } from '../components/ButtonGoBack';
import { Footer } from '../components/Footer';
import { EventCard } from '../components/EventCard';
import { Registrationlist } from '../components/Registrationlist';
import { Resources } from '../components/Resources';
import './Evento.css';

export function Evento() {
  return (
    <div className="event">
      <Header />
      <div className="event-body">
        <ButtonGoBack />
        <EventCard />
        <Registrationlist />
        <Resources />
      </div>
      <Footer />
    </div>
  );
}
