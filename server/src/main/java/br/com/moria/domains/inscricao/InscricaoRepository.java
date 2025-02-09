package br.com.moria.domains.inscricao;

import java.util.List;
import java.util.Optional;

import br.com.moria.domains.inscricao.enums.InscricaoStatusParticipacao;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório responsável por operações de acesso a dados relacionadas à entidade {@link Inscricao}.
 *
 * <p>Esta interface herda de {@link JpaRepository}, fornecendo métodos CRUD padrão, além de consultas específicas
 * definidas por meio de métodos personalizados.</p>
 *
 * @see Inscricao
 * @see JpaRepository
 */
public interface InscricaoRepository extends JpaRepository<Inscricao, Integer> {

    /**
     * Busca uma inscrição específica de um membro em um evento.
     *
     * @param membroId o ID do membro.
     * @param eventoId o ID do evento.
     * @return um {@link Optional} contendo a inscrição, caso encontrada.
     */
    Optional<Inscricao> findByMembroIdAndEventoId(int membroId, int eventoId);

    /**
     * Verifica se existe uma inscrição com o ID de membro e evento especificados.
     *
     * @param membroId o ID do membro.
     * @param eventoId o ID do evento.
     * @return {@code true} se a inscrição existir, {@code false} caso contrário.
     */
    boolean existsByMembroIdAndEventoId(int membroId, int eventoId);

    /**
     * Verifica se existe uma inscrição com o ID especificado e que esteja marcada como inscrito.
     *
     * @param inscricaoId o ID da inscrição.
     * @return {@code true} se a inscrição existir e estiver marcada como inscrito, {@code false} caso contrário.
     */
    boolean existsByIdAndInscritoTrue(int inscricaoId);

    /**
     * Busca todas as inscrições de um determinado evento.
     *
     * @param eventoId o ID do evento.
     * @return uma lista de inscrições associadas ao evento.
     */
    List<Inscricao> findByEventoId(int eventoId);

    /**
     * Busca todas as inscrições de um determinado membro.
     *
     * @param membroId o ID do membro.
     * @return uma lista de inscrições associadas ao membro.
     */
    List<Inscricao> findByMembroId(int membroId);

    /**
     * Busca todas as inscrições com um status de participação específico.
     *
     * @param status o status de participação a ser buscado.
     * @return uma lista de inscrições com o status de participação especificado.
     */
    List<Inscricao> findByStatusParticipacao(InscricaoStatusParticipacao status);
}
