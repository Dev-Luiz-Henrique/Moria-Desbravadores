package br.com.moria.services.implementations;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.moria.models.Membro;
import br.com.moria.models.Mensalidade;
import br.com.moria.repositories.MembroRepository;
import br.com.moria.repositories.MensalidadeRepository;
import br.com.moria.services.interfaces.IMensalidadeService;
import br.com.moria.services.interfaces.IUploadService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

public class MensalidadeServiceImpl implements IMensalidadeService {
	
	@Autowired
    private MensalidadeRepository mensalidadeRepository;

    @Autowired
    private MembroRepository membroRepository;
    
    @Autowired
    private IUploadService uploadService;

	@Override
	@Scheduled(cron = "0 0 0 1 * ?") 
    @Transactional
    public void gerarMensalidadesMensal() {
        LocalDateTime dataAtual = LocalDateTime.now();
        List<Membro> membrosAtivos = membroRepository.findAll(); //em tese deveria ser findByAtivo();
        
        for (Membro membro : membrosAtivos) {
            if (!existeMensalidadeNoMesAtual(membro, dataAtual)) {
                Mensalidade mensalidade = criarNovaMensalidade(membro, dataAtual);
                mensalidadeRepository.save(mensalidade);
            }
        } 
    }

    public Mensalidade gerarMensalidadeManual(@Valid Membro membro) {
        if (existeMensalidadeNoMesAtual(membro, LocalDateTime.now())) 
            throw new IllegalArgumentException("Já existe mensalidade gerada para este membro no mês atual");
  
        Mensalidade mensalidade = criarNovaMensalidade(membro, LocalDateTime.now());
        return mensalidadeRepository.save(mensalidade);
    }
    
	private boolean existeMensalidadeNoMesAtual(@Valid Membro membro, LocalDateTime dataAtual) {
        LocalDateTime inicioDomes = dataAtual
            .withDayOfMonth(1)
            .withHour(0)
            .withMinute(0)
            .withSecond(0)
            .withNano(0);
            
        LocalDateTime fimDoMes = inicioDomes
            .plusMonths(1)
            .minusNanos(1);

        return mensalidadeRepository.existsByMembroAndDataBetween(membro, inicioDomes, fimDoMes);
    }

    private Mensalidade criarNovaMensalidade(Membro membro, LocalDateTime dataAtual) {
        Mensalidade mensalidade = new Mensalidade();
        mensalidade.setMembro(membro);
        mensalidade.setData(dataAtual);
        mensalidade.setDataVencimento(dataAtual.plusDays(20));
        mensalidade.setValor(15.00);
        mensalidade.setPagamentoRealizado(false);
        
        return mensalidade;
    }
    
    @Override
    public Mensalidade updateComprovantePagamentoById(int id, MultipartFile file) throws IOException {
        Mensalidade existingMensalidade = mensalidadeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Mensalidade não encontrada"));

        String filePath = uploadService.uploadFichaSaude(file);
        existingMensalidade.setComprovante(filePath);
    
        return mensalidadeRepository.save(existingMensalidade);
    }

	@Override
	public List<Mensalidade> findAll() {
		return mensalidadeRepository.findAll();
	}

	@Override
	public List<Mensalidade> findDataInterval(LocalDateTime start, LocalDateTime end) {
		return mensalidadeRepository.findByDataBetween(start, end);
	}

}
