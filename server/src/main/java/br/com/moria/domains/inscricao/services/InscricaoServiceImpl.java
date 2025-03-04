package br.com.moria.domains.inscricao.services;

import java.util.List;

import br.com.moria.domains.inscricao.Inscricao;
import br.com.moria.domains.inscricao.InscricaoMapper;
import br.com.moria.domains.inscricao.InscricaoRepository;
import br.com.moria.domains.inscricao.enums.InscricaoStatusParticipacao;
import br.com.moria.domains.inscricao.dtos.InscricaoCreateDTO;
import br.com.moria.domains.inscricao.dtos.InscricaoResponseDTO;
import br.com.moria.domains.inscricao.dtos.InscricaoUpdateDTO;
import br.com.moria.domains.membro.services.IMembroQueryService;
import br.com.moria.shared.enums.EntityType;
import br.com.moria.shared.exceptions.DuplicatedResourceException;
import br.com.moria.shared.exceptions.NotFoundResourceException;
import br.com.moria.shared.exceptions.ValidationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moria.domains.evento.Evento;
import br.com.moria.domains.membro.Membro;
import br.com.moria.domains.evento.services.IEventoService;

/**
 * Implementação do serviço para operações relacionadas a inscrições.
 *
 * <p>Fornece funcionalidades para criar, atualizar, excluir e consultar inscrições de membros em eventos.</p>
 *
 * @see IInscricaoService
 */
@Service
public class InscricaoServiceImpl implements IInscricaoService{

    private final InscricaoMapper inscricaoMapper;
	private final InscricaoRepository inscricaoRepository;
    private final IMembroQueryService membroQueryService;
    private final IEventoService eventoService;

    /**
     * Construtor para injeção de dependências.
     *
     * @param inscricaoMapper       o mapper para conversão entre DTO e entidade.
     * @param inscricaoRepository   o repositório para operações com a entidade {@link Inscricao}.
     * @param membroQueryService    o serviço para manipulação de membros.
     * @param eventoService         o serviço para manipulação de eventos.
     */
    @Autowired
    public InscricaoServiceImpl(InscricaoMapper inscricaoMapper,
                                InscricaoRepository inscricaoRepository,
                                IMembroQueryService membroQueryService,
                                IEventoService eventoService) {
        this.inscricaoMapper = inscricaoMapper;
        this.inscricaoRepository = inscricaoRepository;
        this.membroQueryService = membroQueryService;
        this.eventoService = eventoService;
    }

    /**
     * Valida se o evento existe.
     *
     * @param eventoId o identificador do evento.
     * @throws NotFoundResourceException se o evento não for encontrado.
     */
    private void validateEvento(int eventoId) {
        if (!eventoService.existsById(eventoId))
            throw NotFoundResourceException.forEntity(EntityType.EVENTO, eventoId);
    }

    /**
     * Valida se o membro existe.
     *
     * @param membroId o identificador do membro.
     * @throws NotFoundResourceException se o membro não for encontrado.
     */
    private void validateMembro(int membroId) {
        if (!membroQueryService.existsById(membroId))
            throw NotFoundResourceException.forEntity(EntityType.MEMBRO, membroId);
    }

    /**
     * Busca uma inscrição pelo ID, lançando uma exceção se não for encontrada.
     *
     * @param id o identificador da inscrição.
     * @return a inscrição encontrada.
     * @throws NotFoundResourceException se a inscrição não for encontrada.
     */
    private Inscricao findInscricaoById(int id) {
        return inscricaoRepository.findById(id)
                .orElseThrow(() -> NotFoundResourceException.forEntity(EntityType.INSCRICAO, id));
    }

    @Override
    public long count() {
        return inscricaoRepository.count();
    }

	@Override
	public InscricaoResponseDTO create(@NotNull InscricaoCreateDTO inscricaoCreateDTO) {
        validateEvento(inscricaoCreateDTO.getEventoId());
        validateMembro(inscricaoCreateDTO.getMembroId());

        if (inscricaoRepository.existsByMembroIdAndEventoId(
                inscricaoCreateDTO.getMembroId(), inscricaoCreateDTO.getEventoId()))
            throw DuplicatedResourceException.forEntity(EntityType.INSCRICAO, "business.inscricao.duplicated");

        Evento evento = eventoService.findEventoById(inscricaoCreateDTO.getEventoId());
        Membro membro = membroQueryService.findMembroById(inscricaoCreateDTO.getMembroId());
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
        Membro membro = membroQueryService.findMembroById(inscricaoUpdateDTO.getMembroId());
        Inscricao inscricao = inscricaoMapper.toEntity(inscricaoUpdateDTO);
        inscricao.setEvento(evento);
        inscricao.setMembro(membro);

        Inscricao updatedInscricao = inscricaoRepository.save(inscricao);
        return inscricaoMapper.toResponseDTO(updatedInscricao);
	}

	@Override
	public InscricaoResponseDTO updateStatusInscricao(int membroId, int eventoId) {
        Inscricao existingInscricao = inscricaoRepository.findByMembroIdAndEventoId(membroId, eventoId)
                .orElseThrow(() -> NotFoundResourceException.forEntity(
                        EntityType.INSCRICAO, "business.inscricao.not_found_membro_evento"));

        if (existingInscricao.getInscrito())
            throw ValidationException.of("business.inscricao.already_registered");

        existingInscricao.setInscrito(true);
        Inscricao updatedInscricao = inscricaoRepository.save(existingInscricao);
        return inscricaoMapper.toResponseDTO(updatedInscricao);
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
        validateEvento(eventoId);
		List<Inscricao> inscricoes = inscricaoRepository.findByEventoId(eventoId);
        return inscricaoMapper.toResponseDTO(inscricoes);
	}
}
