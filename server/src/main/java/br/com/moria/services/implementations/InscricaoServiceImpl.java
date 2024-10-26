package br.com.moria.services.implementations;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.moria.enums.StatusParticipacao;
import br.com.moria.models.Evento;
import br.com.moria.models.Inscricao;
import br.com.moria.models.Membro;
import br.com.moria.repositories.EventoRepository;
import br.com.moria.repositories.InscricaoRepository;
import br.com.moria.repositories.MembroRepository;
import br.com.moria.services.interfaces.IFileService;
import br.com.moria.services.interfaces.IInscricaoService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class InscricaoServiceImpl implements IInscricaoService{

	@Autowired
	private InscricaoRepository inscricaoRepository;

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private MembroRepository membroRepository;

	@Autowired
    private IFileService uploadService;

	@Override
	public Inscricao create(Inscricao inscricao) {
		Evento evento = inscricao.getEvento();
		inscricao.setEvento(eventoRepository.findById(evento.getId())
				.orElseGet(() -> eventoRepository.save(evento)));

		Membro membro = inscricao.getMembro();
		inscricao.setMembro(membroRepository.findById(membro.getId())
				.orElseGet(() -> membroRepository.save(membro)));

		return inscricaoRepository.save(inscricao);
	}

	@Override
	public Inscricao updateStatusParticipacao(int id, StatusParticipacao status) {
		Inscricao existingInscricao = inscricaoRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Inscricao não encontrada"));

		existingInscricao.setStatusParticipacao(status);

		return inscricaoRepository.save(existingInscricao);
	}

	@Override
	public void delete(int id) {
		if (!inscricaoRepository.existsById(id)) {
			throw new EntityNotFoundException("Inscricao não encontrada para o ID fornecido.");
		}

        inscricaoRepository.deleteById(id);
	}

	@Override
	public List<Inscricao> findAll() {
		return inscricaoRepository.findAll();
	}

	@Override
	public List<Inscricao> findStatusParticipacao(StatusParticipacao status) {
		return inscricaoRepository.findByStatusParticipacao(status);
	}

    @Override
	public Inscricao updateAutorizacaoResponsavel(int id, MultipartFile file) throws IOException {
		Inscricao existingInscricao = inscricaoRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Inscricao não encontrada"));

		String filePath = uploadService.uploadAutorizacaoResponsavel(file);
		existingInscricao.setAutorizacao(filePath);

		return inscricaoRepository.save(existingInscricao);
	}
}
