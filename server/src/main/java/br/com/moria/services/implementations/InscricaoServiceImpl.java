package br.com.moria.services.implementations;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moria.enums.StatusParticipacao;
import br.com.moria.models.Inscricao;
import br.com.moria.repositories.EventoRepository;
import br.com.moria.repositories.InscricaoRepository;
import br.com.moria.repositories.MembroRepository;
import br.com.moria.services.interfaces.IInscricaoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class InscricaoServiceImpl implements IInscricaoService{

	@Autowired
	private InscricaoRepository inscricaoRepository;

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private MembroRepository membroRepository;

	@Override
	public Inscricao create(@Valid @NotNull Inscricao inscricao) {
		inscricao.setEvento(
            eventoRepository.findById(inscricao.getEvento().getId())
                .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado"))
        );

		inscricao.setMembro(
            membroRepository.findById(inscricao.getMembro().getId())
                .orElseThrow(() -> new EntityNotFoundException("Membro não encontrado"))
		);
		return inscricaoRepository.save(inscricao);
	}

	@Override
	public Inscricao update(@Valid @NotNull Inscricao inscricao) {
        Inscricao existingInscricao = inscricaoRepository.findById(inscricao.getId())
            .orElseThrow(() -> new EntityNotFoundException("Inscricao não encontrada"));

        if (inscricao.getStatusParticipacao() != null) {
			existingInscricao.setStatusParticipacao(inscricao.getStatusParticipacao());
		}
        if (inscricao.getInscrito() != null) {
			existingInscricao.setInscrito(inscricao.getInscrito());
		}

		return inscricaoRepository.save(existingInscricao);
	}

	@Override
	public Inscricao updateStatusInscricao(int membroId, int eventoId) {
		Inscricao existingInscricao = inscricaoRepository.findByMembroIdAndEventoId(membroId, eventoId)
			.orElseThrow(() -> new EntityNotFoundException("Membro não elegivel para o evento"));
		
		if (existingInscricao.getInscrito() == true) 
			throw new IllegalArgumentException("Inscrição já feita");
		
		existingInscricao.setInscrito(true);
		return inscricaoRepository.save(existingInscricao);
	}

	@Override
	public void delete(int id) {
        Inscricao existingInscricao = inscricaoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Inscricao não encontrada"));

        inscricaoRepository.delete(existingInscricao);
	}

	@Override
	public List<Inscricao> findAll() {
		return inscricaoRepository.findAll();
	}

    @Override
    public Inscricao findById(int id) {
        return inscricaoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Inscricao não encontrada"));
    }

	@Override
	public List<Inscricao> findByStatusParticipacao(StatusParticipacao status) {
		return inscricaoRepository.findByStatusParticipacao(status);
	}

    @Override
    public boolean isInscrito(int id) {
        return inscricaoRepository.existsByIdAndInscritoTrue(id);
    }

	@Override
	public List<Inscricao> findInscricoesByEventoId(int eventoId) {
        /*if (!eventoRepository.existsById(eventoId)) {
            throw new IllegalArgumentException("Evento não encontrado para o ID fornecido.");
        }
		List<Inscricao> inscricoes = inscricaoRepository.findByEventoId(eventoId);
        inscricoes.forEach(inscricao -> {
            inscricao.getEvento().setRecursos(Collections.emptyList());
        });

        return inscricoes;*/
		return null;
	}
}