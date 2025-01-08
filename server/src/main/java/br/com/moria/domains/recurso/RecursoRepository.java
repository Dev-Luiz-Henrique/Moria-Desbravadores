package br.com.moria.domains.recurso;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Repositório responsável por operações de acesso a dados relacionadas à entidade {@link Recurso}.
 *
 * <p>Esta interface herda de {@link JpaRepository}, fornecendo métodos CRUD padrão, além de consultas específicas
 * definidas por meio de métodos personalizados.</p>
 *
 * @see Recurso
 * @see JpaRepository
 * */
public interface RecursoRepository extends JpaRepository<Recurso, Integer> {

    /**
     * Busca recursos associados a um evento específico.
     *
     * @param eventoId o ID do evento.
     * @return uma lista de recursos associados ao evento.
     */
    List<Recurso> findByEventoId(int eventoId);
}
