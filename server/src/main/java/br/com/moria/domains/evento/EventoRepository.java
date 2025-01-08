package br.com.moria.domains.evento;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório responsável por operações de acesso a dados relacionadas à entidade {@link Evento}.
 *
 * <p>Esta interface herda de {@link JpaRepository}, fornecendo métodos CRUD padrão, além de consultas específicas
 * definidas por meio de métodos personalizados.</p>
 *
 * @see Evento
 * @see JpaRepository
 */
public interface EventoRepository extends JpaRepository<Evento, Integer> {

	/**
	 * Verifica se existe um evento com o ID especificado.
	 *
	 * @param id o ID do evento.
	 * @return {@code true} se o evento existir, {@code false} caso contrário.
	 */
	boolean existsById(int id);

	/**
	 * Busca eventos cujo nome contenha a palavra-chave especificada.
	 *
	 * @param palavraChave a palavra-chave a ser buscada no nome do evento.
	 * @return uma lista de eventos cujos nomes contêm a palavra-chave.
	 */
	List<Evento> findByNomeContaining(String palavraChave);

	/**
	 * Busca eventos que ocorram exatamente na data de início especificada.
	 *
	 * @param data a data de início do evento.
	 * @return uma lista de eventos que começam na data especificada.
	 */
	List<Evento> findByDataInicio(LocalDateTime data);

	/**
	 * Busca eventos que terminem exatamente na data de fim especificada.
	 *
	 * @param data a data de fim do evento.
	 * @return uma lista de eventos que terminam na data especificada.
	 */
	List<Evento> findByDataFim(LocalDateTime data);

	/**
	 * Busca eventos que ocorram dentro de um intervalo de datas.
	 *
	 * @param start a data de início do intervalo.
	 * @param end a data de fim do intervalo.
	 * @return uma lista de eventos que ocorrem dentro do intervalo de datas.
	 */
	List<Evento> findByDataInicioBetween(LocalDateTime start, LocalDateTime end);
}
