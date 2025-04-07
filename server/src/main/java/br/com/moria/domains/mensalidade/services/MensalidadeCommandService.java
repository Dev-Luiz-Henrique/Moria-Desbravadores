package br.com.moria.domains.mensalidade.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import br.com.moria.domains.membro.services.IMembroQueryService;
import br.com.moria.domains.mensalidade.Mensalidade;
import br.com.moria.domains.mensalidade.MensalidadeMapper;
import br.com.moria.domains.mensalidade.MensalidadeRepository;
import br.com.moria.domains.mensalidade.dtos.MensalidadeCreateDTO;
import br.com.moria.domains.mensalidade.dtos.MensalidadeResponseDTO;
import br.com.moria.shared.enums.EntityType;
import br.com.moria.shared.exceptions.DuplicatedResourceException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.moria.shared.enums.FormaPagamento;
import br.com.moria.domains.membro.Membro;
import br.com.moria.domains.file.IFileService;

/**
 * Implementação do serviço para operações relacionadas à gestão de mensalidades.
 *
 * <p>Fornece funcionalidades para criação, atualização e exclusão de mensalidades,
 * bem como operações específicas.</p>
 *
 * @see IMensalidadeCommandService
 */
@Service
public class MensalidadeCommandService implements IMensalidadeCommandService {

    private final MensalidadeMapper mensalidadeMapper;
    private final MensalidadeRepository mensalidadeRepository;
    private final IMensalidadeQueryService mensalidadeQueryService;
    private final IMembroQueryService membroQueryService;
    private final IFileService fileService;

    @Value("${mensalidade.valor:15.00}")
    private double value;

    /**
     * Construtor para injeção de dependências.
     *
     * @param mensalidadeMapper     o mapper para conversão entre DTO e entidade.
     * @param mensalidadeRepository o repositório para operações com a entidade {@link Mensalidade}.
     * @param membroQueryService    o serviço para manipulação de membros.
     * @param fileService           o serviço para manipulação de arquivos.
     */
    @Autowired
    public MensalidadeCommandService(MensalidadeMapper mensalidadeMapper,
                                  MensalidadeRepository mensalidadeRepository,
                                  IMensalidadeQueryService mensalidadeQueryService,
                                  IMembroQueryService membroQueryService,
                                  IFileService fileService ) {
        this.mensalidadeMapper = mensalidadeMapper;
        this.mensalidadeRepository = mensalidadeRepository;
        this.mensalidadeQueryService = mensalidadeQueryService;
        this.membroQueryService = membroQueryService;
        this.fileService = fileService;
    }

    /**
     * Constrói uma nova mensalidade para um membro ativo.
     *
     * @param membro o membro associado à mensalidade.
     * @return a mensalidade criada.
     */
    private Mensalidade buildMensalidade(Membro membro) {
        LocalDateTime currentDate = LocalDateTime.now();
        Mensalidade mensalidade = new Mensalidade();
        mensalidade.setMembro(membro);
        mensalidade.setData(currentDate);
        mensalidade.setDataVencimento(currentDate.plusDays(20));
        mensalidade.setValor(value);
        mensalidade.setPagamentoRealizado(false);
        return mensalidade;
    }

    /**
     * Verifica se já existe uma mensalidade gerada para um membro no mês atual.
     *
     * @param membro o membro a ser verificado.
     * @return true se já existe uma mensalidade para o membro no mês atual, false caso contrário.
     */
    private boolean doesMensalidadeExist(@NotNull Membro membro) {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime startOfMonth = currentDate.withDayOfMonth(1).toLocalDate().atStartOfDay();
        LocalDateTime endOfMonth = startOfMonth.plusMonths(1).minusNanos(1);
        return mensalidadeRepository.existsByMembroIdAndDataBetween(membro.getId(), startOfMonth, endOfMonth);
    }

    @Override
	@Scheduled(cron = "0 0 0 1 * ?")
    public void createAuto() {
        List<Membro> membrosAtivos = membroQueryService.findAllMembrosByAtivo(true);
        
        for (Membro membro : membrosAtivos) {
            if (!doesMensalidadeExist(membro)) {
                Mensalidade mensalidade = buildMensalidade(membro);
                mensalidadeRepository.save(mensalidade);
            }
        }
    }

    @Override
    public MensalidadeResponseDTO createManual(int idMembro) {
        Membro membro = membroQueryService.findMembroById(idMembro);
        if (doesMensalidadeExist(membro))
            throw DuplicatedResourceException.forEntity(EntityType.MENSALIDADE, "business.mensalidade.duplicated");

        Mensalidade mensalidade = buildMensalidade(membro);
        Mensalidade savedMensalidade = mensalidadeRepository.save(mensalidade);
        return mensalidadeMapper.toResponseDTO(savedMensalidade);
    }

    @Override
    public MensalidadeResponseDTO create(@NotNull MensalidadeCreateDTO mensalidadeCreateDTO) {
        Membro membro = membroQueryService.findMembroById(mensalidadeCreateDTO.getIdMembro());
        if (doesMensalidadeExist(membro))
            throw new IllegalArgumentException("Já existe mensalidade gerada para este membro no mês atual");

        Mensalidade mensalidade = mensalidadeMapper.toEntity(mensalidadeCreateDTO);
        mensalidade.setMembro(membro);
        Mensalidade savedMensalidade = mensalidadeRepository.save(mensalidade);
        return mensalidadeMapper.toResponseDTO(savedMensalidade);
    }

    @Override
    public MensalidadeResponseDTO updatePagamentoById(int id, FormaPagamento formaPagamento, MultipartFile file) throws IOException {
        Mensalidade existingMensalidade = mensalidadeQueryService.findMensalidadeById(id);
        String filePath = fileService.uploadFile(file, "mensalidade");

        LocalDateTime today = LocalDateTime.now();
        existingMensalidade.setPagamentoRealizado(true);
        existingMensalidade.setFormaPagamento(formaPagamento);
        existingMensalidade.setDataPagamento(today);
        existingMensalidade.setComprovante(filePath);

        Mensalidade updatedMensalidade = mensalidadeRepository.save(existingMensalidade);
        return mensalidadeMapper.toResponseDTO(updatedMensalidade);
    }
}
