package br.com.moria.domains.mensalidade;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório responsável por operações de acesso a dados relacionadas à entidade {@link Mensalidade}.
 *
 * <p>Esta interface herda de {@link JpaRepository}, fornecendo métodos CRUD padrão, além de consultas específicas
 * definidas por meio de métodos personalizados.</p>
 *
 * @see Mensalidade
 * @see JpaRepository
 */
public interface MensalidadeRepository extends JpaRepository<Mensalidade, Integer> {

    /**
     * Verifica se existe uma mensalidade associada a um membro no intervalo de datas informado.
     *
     * @param membroId o identificador do membro cuja mensalidade está a ser verificada.
     * @param start a data de início do intervalo.
     * @param end a data de fim do intervalo.
     * @return {@code true} se existir uma mensalidade para o membro no intervalo, {@code false} caso contrário.
     */
    boolean existsByMembroIdAndDataBetween(Integer membroId, LocalDateTime start, LocalDateTime end);

    /**
     * Busca mensalidades de um membro específico dentro de um intervalo de datas.
     *
     * @param membroId o identificador do membro cujas mensalidades estão a ser buscadas.
     * @param start a data de início do intervalo.
     * @param end a data de fim do intervalo.
     * @return uma lista de mensalidades do membro dentro do intervalo informado.
     */
    List<Mensalidade> findByMembroIdAndDataBetween(Integer membroId, LocalDateTime start, LocalDateTime end);

    /**
     * Busca todas as mensalidades de um membro específico.
     *
     * @param membroId o identificador do membro cujas mensalidades estão a ser buscadas.
     * @return uma lista de mensalidades associadas ao membro.
     */
    List<Mensalidade> findByMembroId(Integer membroId);

    /**
     * Busca mensalidades dentro de um intervalo de datas.
     *
     * @param start a data de início do intervalo.
     * @param end a data de fim do intervalo.
     * @return uma lista de mensalidades dentro do intervalo informado.
     */
    List<Mensalidade> findByDataBetween(LocalDateTime start, LocalDateTime end);
}
