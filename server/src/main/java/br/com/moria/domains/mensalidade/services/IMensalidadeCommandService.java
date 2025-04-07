package br.com.moria.domains.mensalidade.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import br.com.moria.domains.mensalidade.dtos.MensalidadeCreateDTO;
import br.com.moria.domains.mensalidade.dtos.MensalidadeResponseDTO;
import br.com.moria.shared.enums.FormaPagamento;
import br.com.moria.shared.exceptions.DuplicatedResourceException;
import br.com.moria.shared.exceptions.NotFoundResourceException;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interface de serviço para operações relacionadas a mensalidades.
 *
 * <p>Define métodos para manipulação de dados de mensalidades, incluindo criação,
 * atualização, exclusão e operações específicas.</p>
 *
 * @see MensalidadeResponseDTO
 * @see FormaPagamento
 */
public interface IMensalidadeCommandService {

    /**
     * Cria mensalidades automaticamente para todos os membros ativos no início do mês.
     *
     * <p>Esta função é agendada para rodar automaticamente no dia 1º do todos os meses.</p>
     */
    void createAuto();

    /**
     * Cria uma mensalidade manualmente para um membro específico.
     *
     * @param idMembro o identificador do membro.
     * @return os detalhes da mensalidade criada.
     * @throws NotFoundResourceException se o membro não for encontrado.
     * @throws DuplicatedResourceException se já existir uma mensalidade para o membro no mês atual.
     */
    MensalidadeResponseDTO createManual(int idMembro);

    /**
     * Cria uma nova mensalidade com base nos dados fornecidos.
     *
     * @param mensalidadeCreateDTO os dados da mensalidade a ser criada.
     * @return os detalhes da mensalidade criada.
     * @throws NotFoundResourceException se o membro associado à mensalidade não for encontrado.
     * @throws DuplicatedResourceException se já existir uma mensalidade para o membro no mês atual.
     */
    MensalidadeResponseDTO create(MensalidadeCreateDTO mensalidadeCreateDTO);

    /**
     * Atualiza o pagamento de uma mensalidade pelo ID, registrando a forma de pagamento e
     * salvando o comprovante enviado.
     *
     * @param id o identificador da mensalidade.
     * @param formaPagamento a forma de pagamento utilizada.
     * @param file o arquivo do comprovante de pagamento.
     * @return os detalhes da mensalidade com o pagamento atualizado.
     * @throws IOException se houver erro ao processar o arquivo.
     * @throws NotFoundResourceException se a mensalidade não for encontrada.
     */
    MensalidadeResponseDTO updatePagamentoById(int id, FormaPagamento formaPagamento, MultipartFile file) throws IOException;
}
