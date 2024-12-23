package br.com.moria.services.interfaces;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.moria.dtos.Mensalidade.MensalidadeResponseDTO;
import br.com.moria.enums.FormaPagamento;

/**
 * Interface de serviço para operações relacionadas a mensalidades.
 *
 * <p>Define métodos para manipulação de dados de mensalidades, incluindo criação,
 * atualização, consultas e e operações específicas.</p>
 *
 * @see MensalidadeResponseDTO
 * @see FormaPagamento
 */
public interface IMensalidadeService {

    /**
     * Cria mensalidades automaticamente para todos os membros ativos.
     */
    void createMensalidadeAuto();

    /**
     * Cria uma mensalidade para um membro específico.
     *
     * @param idMembro o identificador do membro.
     * @return os detalhes da mensalidade criada.
     */
    MensalidadeResponseDTO create(int idMembro);

    /**
     * Retorna todas as mensalidades cadastradas.
     *
     * @return uma lista de todas as mensalidades.
     */
    List<MensalidadeResponseDTO> findAll();

    /**
     * Busca mensalidades pelo ID.
     *
     * @param id o identificador da mensalidade.
     * @return os detalhes da mensalidade encontrada.
     */
    MensalidadeResponseDTO findById(int id);

    /**
     * Busca mensalidades dentro de um intervalo de datas.
     *
     * @param start a data inicial do intervalo.
     * @param end a data final do intervalo.
     * @return uma lista de mensalidades dentro do intervalo especificado.
     */
    List<MensalidadeResponseDTO> findByDateInterval(LocalDateTime start, LocalDateTime end);

    /**
     * Busca mensalidades de um membro específico dentro de um intervalo de datas.
     *
     * @param idMembro o identificador do membro.
     * @param start a data inicial do intervalo.
     * @param end a data final do intervalo.
     * @return uma lista de mensalidades do membro dentro do intervalo especificado.
     */
    List<MensalidadeResponseDTO> findByMembroAndDateInterval(int idMembro, LocalDateTime start, LocalDateTime end);

    /**
     * Busca todas as mensalidades de um membro específico.
     *
     * @param idMembro o identificador do membro.
     * @return uma lista de mensalidades do membro.
     */
    List<MensalidadeResponseDTO> findByMembro(int idMembro);

    /**
     * Atualiza o pagamento de uma mensalidade pelo ID, registrando a forma de pagamento e
     * salvando o comprovante enviado.
     *
     * @param id o identificador da mensalidade.
     * @param formaPagamento a forma de pagamento utilizada.
     * @param file o arquivo do comprovante de pagamento.
     * @return os detalhes da mensalidade com o pagamento atualizado.
     * @throws IOException se houver erro ao processar o arquivo.
     */
    MensalidadeResponseDTO updatePagamentoById(int id, FormaPagamento formaPagamento, MultipartFile file) throws IOException;
}
