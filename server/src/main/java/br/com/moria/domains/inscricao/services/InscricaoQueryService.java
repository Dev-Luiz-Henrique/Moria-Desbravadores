package br.com.moria.domains.inscricao.services;

import java.util.List;

import br.com.moria.domains.evento.services.IEventoQueryService;
import br.com.moria.domains.inscricao.Inscricao;
import br.com.moria.domains.inscricao.InscricaoMapper;
import br.com.moria.domains.inscricao.InscricaoRepository;
import br.com.moria.domains.inscricao.enums.InscricaoStatusParticipacao;
import br.com.moria.domains.inscricao.dtos.InscricaoResponseDTO;
import br.com.moria.domains.recurso.services.IRecursoQueryService;
import br.com.moria.shared.enums.EntityType;
import br.com.moria.shared.exceptions.NotFoundResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementação do serviço para operações relacionadas a visualização de incrições.
 *
 * <p>Fornece funcionalidades para consulta de incrições, como obter dados detalhados de uma inscrição
 * e listar incrições com base em diferentes critérios.</p>
 *
 * @see IRecursoQueryService
 */
@Service
public class InscricaoQueryService implements IInscricaoQueryService {

    private final InscricaoMapper inscricaoMapper;
	private final InscricaoRepository inscricaoRepository;
    private final IEventoQueryService eventoQueryService;

    /**
     * Construtor para injeção de dependências.
     *
     * @param inscricaoMapper       o mapper para conversão entre DTO e entidade.
     * @param inscricaoRepository   o repositório para operações com a entidade {@link Inscricao}.
     * @param eventoQueryService    o serviço para manipulação de eventos.
     */
    @Autowired
    public InscricaoQueryService(InscricaoMapper inscricaoMapper,
                                 InscricaoRepository inscricaoRepository,
                                 IEventoQueryService eventoQueryService) {
        this.inscricaoMapper = inscricaoMapper;
        this.inscricaoRepository = inscricaoRepository;
        this.eventoQueryService = eventoQueryService;
    }

    @Override
    public Inscricao findInscricaoById(int id) {
        return inscricaoRepository.findById(id)
                .orElseThrow(() -> NotFoundResourceException.forEntity(EntityType.INSCRICAO, id));
    }

    @Override
    public long count() {
        return inscricaoRepository.count();
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
    public List<InscricaoResponseDTO> findByStatusParticipacao(InscricaoStatusParticipacao status) {
        List<Inscricao> inscricao = inscricaoRepository.findByStatusParticipacao(status);
        return inscricaoMapper.toResponseDTO(inscricao);
    }

    @Override
    public boolean isInscrito(int id) {
        return inscricaoRepository.existsByIdAndInscritoTrue(id);
    }

	@Override
	public List<InscricaoResponseDTO> findInscricoesByEventoId(int eventoId) {
        eventoQueryService.assertEventoExists(eventoId);
		List<Inscricao> inscricoes = inscricaoRepository.findByEventoId(eventoId);
        return inscricaoMapper.toResponseDTO(inscricoes);
	}
}
