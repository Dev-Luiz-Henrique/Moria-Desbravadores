package br.com.moria.services.implementations;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

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

@Service
public class MensalidadeServiceImpl implements IMensalidadeService {

    private final MensalidadeMapper mensalidadeMapper;
    private final MensalidadeRepository mensalidadeRepository;
    private final IMembroService membroService;
    private final IFileService fileService;

    @Value("${mensalidade.valor:15.00}")
    private double value;

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

    private Mensalidade findMensalidadeById(int id) {
        return mensalidadeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Mensalidade não encontrada"));
    }

    private @NotNull Mensalidade buildMensalidade(Membro membro) {
        LocalDateTime currentDate = LocalDateTime.now();
        Mensalidade mensalidade = new Mensalidade();
        mensalidade.setMembro(membro);
        mensalidade.setData(currentDate);
        mensalidade.setDataVencimento(currentDate.plusDays(20));
        mensalidade.setValor(value);
        mensalidade.setPagamentoRealizado(false);
        return mensalidade;
    }

    private LocalDateTime [] getMonthInterval(@NotNull LocalDateTime currentDate) {
        LocalDateTime startOfMonth = currentDate.withDayOfMonth(1).toLocalDate().atStartOfDay();
        LocalDateTime endOfMonth = startOfMonth.plusMonths(1).minusNanos(1);
        return new LocalDateTime[]{startOfMonth, endOfMonth};
    }

    private boolean doesMensalidadeExist(@NotNull Membro membro) {
        LocalDateTime currentDate = LocalDateTime.now(); 
        LocalDateTime[] interval = getMonthInterval(currentDate);
        return mensalidadeRepository.existsByMembroIdAndDataBetween(membro.getId(), interval[0], interval[1]);
    }

    @Override
	@Scheduled(cron = "0 0 0 1 * ?")
    public void createMensalidadeAuto() {
        List<Membro> membrosAtivos = membroService.findAllMembrosByAtivo(true);
        
        for (Membro membro : membrosAtivos) {
            if (!doesMensalidadeExist(membro)) {
                Mensalidade mensalidade = buildMensalidade(membro);
                mensalidadeRepository.save(mensalidade);
            }
        }
    }
    
    @Override
    public MensalidadeResponseDTO create(int idMembro) {
        Membro membro = membroService.findMembroById(idMembro);
        if (doesMensalidadeExist(membro))
            throw new IllegalArgumentException("Já existe mensalidade gerada para este membro no mês atual");

        Mensalidade mensalidade = buildMensalidade(membro);
        Mensalidade savedMensalidade = mensalidadeRepository.save(mensalidade);
        return mensalidadeMapper.toResponseDTO(savedMensalidade);
    }

    @Override
    public List<MensalidadeResponseDTO> findAll() {
        List<Mensalidade> mensalidades = mensalidadeRepository.findAll();
        return mensalidadeMapper.toResponseDTO(mensalidades);
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
