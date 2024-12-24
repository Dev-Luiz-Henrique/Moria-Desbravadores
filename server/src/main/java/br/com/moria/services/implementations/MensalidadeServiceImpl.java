package br.com.moria.services.implementations;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import br.com.moria.dtos.Mensalidade.MensalidadeCreateDTO;
import br.com.moria.mappers.MensalidadeMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.moria.dtos.Mensalidade.MensalidadeResponseDTO;
import br.com.moria.enums.FormaPagamento;
import br.com.moria.models.Membro;
import br.com.moria.models.Mensalidade;
import br.com.moria.repositories.MensalidadeRepository;
import br.com.moria.services.interfaces.IFileService;
import br.com.moria.services.interfaces.IMembroService;
import br.com.moria.services.interfaces.IMensalidadeService;
import jakarta.persistence.EntityNotFoundException;

/**
 * Implementação do serviço para operações relacionadas a mensalidades.
 *
 * <p>Fornece funcionalidades para criar, atualizar, consultar e gerenciar mensalidades, incluindo a geração automática mensal.</p>
 *
 * @see IMensalidadeService
 */
@Service
public class MensalidadeServiceImpl implements IMensalidadeService {

    private final MensalidadeMapper mensalidadeMapper;
    private final MensalidadeRepository mensalidadeRepository;
    private final IMembroService membroService;
    private final IFileService fileService;

    @Value("${mensalidade.valor:15.00}")
    private double value;

    /**
     * Construtor para injeção de dependências.
     *
     * @param mensalidadeMapper     o mapper para conversão entre DTO e entidade.
     * @param mensalidadeRepository o repositório para operações com a entidade {@link Mensalidade}.
     * @param membroService         o serviço para manipulação de membros.
     * @param fileService           o serviço para manipulação de arquivos.
     */
    @Autowired
    public MensalidadeServiceImpl(MensalidadeMapper mensalidadeMapper,
                                  MensalidadeRepository mensalidadeRepository,
                                  IMembroService membroService,
                                  IFileService fileService ) {
        this.mensalidadeMapper = mensalidadeMapper;
        this.mensalidadeRepository = mensalidadeRepository;
        this.membroService = membroService;
        this.fileService = fileService;
    }

    /**
     * Busca uma mensalidade pelo ID, lançando uma exceção se não for encontrada.
     *
     * @param id o identificador da mensalidade.
     * @return a mensalidade encontrada.
     * @throws EntityNotFoundException se a mensalidade não for encontrada.
     */
    private Mensalidade findMensalidadeById(int id) {
        return mensalidadeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Mensalidade não encontrada"));
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
     * Obtém o intervalo do mês atual.
     *
     * @param currentDate a data atual.
     * @return um array contendo o início e o fim do mês.
     */
    private LocalDateTime [] getMonthInterval(@NotNull LocalDateTime currentDate) {
        LocalDateTime startOfMonth = currentDate.withDayOfMonth(1).toLocalDate().atStartOfDay();
        LocalDateTime endOfMonth = startOfMonth.plusMonths(1).minusNanos(1);
        return new LocalDateTime[]{startOfMonth, endOfMonth};
    }

    /**
     * Verifica se uma mensalidade já existe para o membro no mês atual.
     *
     * @param membro o membro a ser verificado.
     * @return {@code true} se a mensalidade já existir, caso contrário {@code false}.
     */
    private boolean doesMensalidadeExist(@NotNull Membro membro) {
        LocalDateTime currentDate = LocalDateTime.now(); 
        LocalDateTime[] interval = getMonthInterval(currentDate);
        return mensalidadeRepository.existsByMembroIdAndDataBetween(membro.getId(), interval[0], interval[1]);
    }

    @Override
	@Scheduled(cron = "0 0 0 1 * ?")
    public void createAuto() {
        List<Membro> membrosAtivos = membroService.findAllMembrosByAtivo(true);
        
        for (Membro membro : membrosAtivos) {
            if (!doesMensalidadeExist(membro)) {
                Mensalidade mensalidade = buildMensalidade(membro);
                mensalidadeRepository.save(mensalidade);
            }
        }
    }

    @Override
    public MensalidadeResponseDTO createManual(int idMembro) {
        Membro membro = membroService.findMembroById(idMembro);
        if (doesMensalidadeExist(membro))
            throw new IllegalArgumentException("Já existe mensalidade gerada para este membro no mês atual");

        Mensalidade mensalidade = buildMensalidade(membro);
        Mensalidade savedMensalidade = mensalidadeRepository.save(mensalidade);
        return mensalidadeMapper.toResponseDTO(savedMensalidade);
    }

    @Override
    public long count() {
        return mensalidadeRepository.count();
    }

    @Override
    public MensalidadeResponseDTO create(@NotNull MensalidadeCreateDTO mensalidadeCreateDTO) {
        Membro membro = membroService.findMembroById(mensalidadeCreateDTO.getIdMembro());
        if (doesMensalidadeExist(membro))
            throw new IllegalArgumentException("Já existe mensalidade gerada para este membro no mês atual");

        Mensalidade mensalidade = mensalidadeMapper.toEntity(mensalidadeCreateDTO);
        Mensalidade savedMensalidade = mensalidadeRepository.save(mensalidade);
        return mensalidadeMapper.toResponseDTO(savedMensalidade);
    }

    @Override
    public List<MensalidadeResponseDTO> findAll() {
        List<Mensalidade> mensalidades = mensalidadeRepository.findAll();
        return mensalidadeMapper.toResponseDTO(mensalidades);
    }

    @Override
    public MensalidadeResponseDTO findById(int id) {
        Mensalidade mensalidade = findMensalidadeById(id);
        return mensalidadeMapper.toResponseDTO(mensalidade);
    }

    @Override
    public List<MensalidadeResponseDTO> findByDateInterval(LocalDateTime start, LocalDateTime end) {
        List<Mensalidade> mensalidades = mensalidadeRepository.findByDataBetween(start, end);
        return mensalidadeMapper.toResponseDTO(mensalidades);
    }

	@Override
	public List<MensalidadeResponseDTO> findByMembroAndDateInterval(int membroId, LocalDateTime start, LocalDateTime end) {
		List<Mensalidade> mensalidades = mensalidadeRepository.findByMembroIdAndDataBetween(membroId, start, end);
        return mensalidadeMapper.toResponseDTO(mensalidades);
	}

    @Override
    public List<MensalidadeResponseDTO> findByMembro(int membroId) {
        List<Mensalidade> mensalidades = mensalidadeRepository.findByMembroId(membroId);
        return mensalidadeMapper.toResponseDTO(mensalidades);
    }

    @Override
    public MensalidadeResponseDTO updatePagamentoById(int id, FormaPagamento formaPagamento, MultipartFile file) throws IOException {
        Mensalidade existingMensalidade = findMensalidadeById(id);
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
