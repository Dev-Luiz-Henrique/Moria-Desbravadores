package br.com.moria.domains.inscricao.services;

import br.com.moria.domains.evento.Evento;
import br.com.moria.domains.evento.services.IEventoQueryService;
import br.com.moria.domains.inscricao.Inscricao;
import br.com.moria.domains.inscricao.InscricaoMapper;
import br.com.moria.domains.inscricao.InscricaoRepository;
import br.com.moria.domains.inscricao.dtos.InscricaoCreateDTO;
import br.com.moria.domains.inscricao.dtos.InscricaoResponseDTO;
import br.com.moria.domains.inscricao.dtos.InscricaoUpdateDTO;
import br.com.moria.domains.membro.Membro;
import br.com.moria.domains.membro.services.IMembroQueryService;
import br.com.moria.domains.recurso.services.IRecursoCommandService;
import br.com.moria.shared.enums.EntityType;
import br.com.moria.shared.exceptions.DuplicatedResourceException;
import br.com.moria.shared.exceptions.NotFoundResourceException;
import br.com.moria.shared.exceptions.ValidationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Interface de serviço para operações relacionadas a incrições.
 *
 * <p>Define métodos para manipulação de dados de incrições, incluindo criação,
 * atualização, exclusão e operações específicas.</p>
 *
 * @see IRecursoCommandService
 */
@Service
public class InscricaoCommandService implements IInscricaoCommandService {

    private final InscricaoMapper inscricaoMapper;
    private final InscricaoRepository inscricaoRepository;
    private final IInscricaoQueryService inscricaoQueryService;
    private final IMembroQueryService membroQueryService;
    private final IEventoQueryService eventoQueryService;

    /**
     * Construtor para injeção de dependências.
     *
     * @param inscricaoMapper       o mapper para conversão entre DTO e entidade.
     * @param inscricaoRepository   o repositório para operações com a entidade {@link Inscricao}.
     * @param inscricaoQueryService o serviço para manipulação de incrições.
     * @param membroQueryService    o serviço para manipulação de membros.
     * @param eventoQueryService    o serviço para manipulação de eventos.
     */
    @Autowired
    public InscricaoCommandService(InscricaoMapper inscricaoMapper,
                                InscricaoRepository inscricaoRepository,
                                IInscricaoQueryService inscricaoQueryService,
                                IMembroQueryService membroQueryService,
                                IEventoQueryService eventoQueryService) {
        this.inscricaoMapper = inscricaoMapper;
        this.inscricaoRepository = inscricaoRepository;
        this.inscricaoQueryService = inscricaoQueryService;
        this.membroQueryService = membroQueryService;
        this.eventoQueryService = eventoQueryService;
    }

    @Override
    public InscricaoResponseDTO create(@NotNull InscricaoCreateDTO inscricaoCreateDTO) {
        eventoQueryService.assertEventoExists(inscricaoCreateDTO.getEventoId());
        membroQueryService.assertMembroExists(inscricaoCreateDTO.getMembroId());

        if (inscricaoRepository.existsByMembroIdAndEventoId(
                inscricaoCreateDTO.getMembroId(), inscricaoCreateDTO.getEventoId()))
            throw DuplicatedResourceException.forEntity(EntityType.INSCRICAO, "business.inscricao.duplicated");

        Evento evento = eventoQueryService.findEventoById(inscricaoCreateDTO.getEventoId());
        Membro membro = membroQueryService.findMembroById(inscricaoCreateDTO.getMembroId());
        Inscricao inscricao = inscricaoMapper.toEntity(inscricaoCreateDTO);
        inscricao.setEvento(evento);
        inscricao.setMembro(membro);

        Inscricao saveInscricao = inscricaoRepository.save(inscricao);
        return inscricaoMapper.toResponseDTO(saveInscricao);
    }

    @Override
    public InscricaoResponseDTO update(@NotNull InscricaoUpdateDTO inscricaoUpdateDTO) {
        inscricaoQueryService.findInscricaoById(inscricaoUpdateDTO.getId());
        eventoQueryService.assertEventoExists(inscricaoUpdateDTO.getEventoId());
        membroQueryService.assertMembroExists(inscricaoUpdateDTO.getMembroId());

        Evento evento = eventoQueryService.findEventoById(inscricaoUpdateDTO.getEventoId());
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
        Inscricao existingInscricao = inscricaoQueryService.findInscricaoById(id);
        inscricaoRepository.delete(existingInscricao);
    }
}
