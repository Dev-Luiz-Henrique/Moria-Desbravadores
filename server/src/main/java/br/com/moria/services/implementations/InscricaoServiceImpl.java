package br.com.moria.services.implementations;

import java.util.List;

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
	public Inscricao create(@Valid Inscricao inscricao) {

        System.out.println("Desgraça\n");
		System.out.println(inscricao);
		inscricao.setEvento(
            eventoRepository.findById(inscricao.getEvento().getId())
                .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado"))
        );

		inscricao.setMembro(
            membroRepository.findById(inscricao.getMembro().getId())
                .orElseThrow(() -> new EntityNotFoundException("Membro não encontrado"))
		);
		System.out.println("PORRA");
		return inscricaoRepository.save(inscricao);
	}

	@Override
	public Inscricao update(@Valid Inscricao inscricao) {
        Inscricao existingInscricao = inscricaoRepository.findById(inscricao.getId())
            .orElseThrow(() -> new EntityNotFoundException("Inscricao não encontrada"));
        
        if (inscricao.getEvento() != null)
            existingInscricao.setEvento(inscricao.getEvento());
        if (inscricao.getMembro() != null)
            existingInscricao.setMembro(inscricao.getMembro());

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
}