import React from 'react';
import './Registrationlist.css';
import DeleteImg from '../assets/img/Delete.svg';

// TODO: ESSES DADOS VIRÃO DA API
const data = [
  { cpf: '383997758-43', nome: 'DANIEL LEITE PEREIRA', função: 'VOLUNTÁRIO' },
  {
    cpf: '687420981-73',
    nome: 'LUIZ HENRIQUE DE SANTANA',
    função: 'DESBRAVADOR',
  },
  { cpf: '204625821-59', nome: 'WESLANIA PAULOVA', função: 'VOLUNTÁRIO' },
  {
    cpf: '881005400-80',
    nome: 'MURLANA BARRILDA SANTANA',
    função: 'DESBRAVADOR',
  },
  {
    cpf: '870049421-63',
    nome: 'PETRA HENRICA DE ASSIS',
    função: 'DESBRAVADOR',
  },
  { cpf: '484181527-96', nome: 'GABRIELA GOMAS', função: 'DESBRAVADOR' },
  { cpf: '280130237-61', nome: 'DIEGO ROCHA', função: 'VOLUNTÁRIO' },
  { cpf: '029092961-05', nome: 'KARL MARX FERNANDS', função: 'DESBRAVADOR' },
];

export function Registrationlist() {
  return (
    <section>
      <header>
        {/* TODO: Esse INPUT só será exibido para quem tiver a autorização adequada */}
        <input type="text" placeholder="Adicionar inscritos" />
        <h3>Inscritos</h3>
        {/* TODO: Esse Botão só será exibido para quem tiver a autorização adequada */}
        <button className="salvar" type="button">
          SALVAR
        </button>
      </header>
      <ul className="registrationList">
        <li className="list-header">
          <span>CPF</span>
          <span>NOME</span>
          <span>FUNÇÃO</span>
        </li>
        {data.map((inscrito) => {
          return (
            // Se esse objeto tiver uma ID, será uma key melhor
            <li key={inscrito.nome}>
              {/* TODO: Esse Botão só será exibido para quem tiver a autorização adequada */}
              <button type="button">
                <img src={DeleteImg} alt="Deletar" />
              </button>
              <span>{inscrito.cpf}</span>
              <span>{inscrito.nome}</span>
              <span>{inscrito.função}</span>
            </li>
          );
        })}
      </ul>
    </section>
  );
}
