package br.com.moria.services.implementations;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moria.dtos.Inscricao.InscricaoCreateDTO;
import br.com.moria.dtos.Inscricao.InscricaoResponseDTO;
import br.com.moria.dtos.Inscricao.InscricaoUpdateDTO;
import br.com.moria.enums.StatusParticipacao;
import br.com.moria.mappers.InscricaoMapper;
import br.com.moria.models.Evento;
import br.com.moria.models.Inscricao;
import br.com.moria.models.Membro;
import br.com.moria.repositories.InscricaoRepository;
import br.com.moria.services.interfaces.IEventoService;
import br.com.moria.services.interfaces.IInscricaoService;
import br.com.moria.services.interfaces.IMembroService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class InscricaoServiceImpl implements IInscricaoService{

    private final InscricaoMapper inscricaoMapper;
	private final InscricaoRepository inscricaoRepository;
    private final IMembroService membroService;
    private final IEventoService eventoService;

    @Autowired
    public InscricaoServiceImpl(InscricaoMapper inscricaoMapper,
                                InscricaoRepository inscricaoRepository,
                                IMembroService membroService,
                                IEventoService eventoService) {
        this.inscricaoMapper = inscricaoMapper;
        this.inscricaoRepository = inscricaoRepository;
        this.membroService = membroService;
        this.eventoService = eventoService;
    }

    private void validateEvento(int eventoId) {
        if (!eventoService.existsById(eventoId))
            throw new EntityNotFoundException("Evento não encontrado para a inscricao fornecida.");
    }

    private void validateMembro(int membroId) {
        if (!membroService.existsById(membroId))
            throw new EntityNotFoundException("Membro não encontrado para a inscricao fornecida.");
    }

    private Inscricao findInscricaoById(int id) {
        return inscricaoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Inscricao não encontrada"));
    }

	@Override
	public InscricaoResponseDTO create(@NotNull InscricaoCreateDTO inscricaoCreateDTO) {
        validateEvento(inscricaoCreateDTO.getEventoId());
        validateMembro(inscricaoCreateDTO.getMembroId());

        Evento evento = eventoService.findEventoById(inscricaoCreateDTO.getEventoId());
        Membro membro = membroService.findMembroById(inscricaoCreateDTO.getMembroId());
        Inscricao inscricao = inscricaoMapper.toEntity(inscricaoCreateDTO);
        inscricao.setEvento(evento);
        inscricao.setMembro(membro);

        Inscricao saveInscricao = inscricaoRepository.save(inscricao);
        return inscricaoMapper.toResponseDTO(saveInscricao);
	}

	@Override
	public InscricaoResponseDTO update(@NotNull InscricaoUpdateDTO inscricaoUpdateDTO) {
        findInscricaoById(inscricaoUpdateDTO.getId());
        validateEvento(inscricaoUpdateDTO.getEventoId());
        validateMembro(inscricaoUpdateDTO.getMembroId());

        Evento evento = eventoService.findEventoById(inscricaoUpdateDTO.getEventoId());
        Membro membro = membroService.findMembroById(inscricaoUpdateDTO.getMembroId());
        Inscricao inscricao = inscricaoMapper.toEntity(inscricaoUpdateDTO);
        inscricao.setEvento(evento);
        inscricao.setMembro(membro);

        Inscricao updatedInscricao = inscricaoRepository.save(inscricao);
        return inscricaoMapper.toResponseDTO(updatedInscricao);
	}

	@Override
	public InscricaoResponseDTO updateStatusInscricao(int membroId, int eventoId) {
		Inscricao existingInscricao = inscricaoRepository.findByMembroIdAndEventoId(membroId, eventoId)
			.orElseThrow(() -> new EntityNotFoundException("Membro não elegivel para o evento"));
		
		if (existingInscricao.getInscrito())
			throw new IllegalArgumentException("Inscrição já feita");
		
		existingInscricao.setInscrito(true);
		return inscricaoMapper.toResponseDTO(existingInscricao);
	}

	@Override
	public void delete(int id) {
        Inscricao existingInscricao = findInscricaoById(id);
        inscricaoRepository.delete(existingInscricao);
	}

	@Override
	public List<InscricaoResponseDTO> findAll() {
		List<Inscricao> inscricoes = inscricaoRepository.findAll();
		return inscricaoMapper.toResponseDTO(inscricoes);
	}

    @Override
    public InscricaoResponseDTO findById(int id) {
        Inscricao existingInscricao = findInscricaoById(id);
		return inscricaoMapper.toResponseDTO(existingInscricao);
    }

    @Override
    public List<InscricaoResponseDTO> findByStatusParticipacao(StatusParticipacao status) {
        List<Inscricao> inscricao = inscricaoRepository.findByStatusParticipacao(status);
        return inscricaoMapper.toResponseDTO(inscricao);
    }

    @Override
    public boolean isInscrito(int id) {
        return inscricaoRepository.existsByIdAndInscritoTrue(id);
    }

	@Override
	public List<InscricaoResponseDTO> findInscricoesByEventoId(int eventoId) {
        validateEvento(eventoId);
		List<Inscricao> inscricoes = inscricaoRepository.findByEventoId(eventoId);
        return inscricaoMapper.toResponseDTO(inscricoes);
	}
}
