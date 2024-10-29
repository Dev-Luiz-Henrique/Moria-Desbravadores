import ImgEvent from '../assets/img/default-event.png';
import EditImg from '../assets/img/Edit.svg';
import DeleteImg from '../assets/img/Delete.svg';
import './EventCard.css';

export function EventCard() {
  return (
    <article className="event-card">
      <img className="event-img" src={ImgEvent} alt="Imagem do Evento" />
      <div className="event-info">
        {/* TODO: ACREDITO QUE ESSE MENU SÓ SERÁ EXIBIDO PARA OS USUARIOS QUE TIVEREM PERMISSÃO */}
        <div className="options2">
          <button type="button">
            <img src={EditImg} alt="Editar" />
          </button>
          <button type="button">
            <img src={DeleteImg} alt="Deletar" />
          </button>
        </div>
        {/* FIM DO MENU */}
        <h3>Campori 2024</h3>
        <p>
          <b>ENDEREÇO:</b> IABC Planalmira - GO
        </p>
        <p>
          <b>DATA:</b> 24-28 de Julho
        </p>
        <p>
          <b>DESCRIÇÃO:</b> Reunião anual do clube dos Desbravadores
        </p>
        <p>
          <b>ATRAÇÃO:</b> ---
        </p>
      </div>
    </article>
  );
}
