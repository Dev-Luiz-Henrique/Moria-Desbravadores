package br.com.moria.domains.mensalidade.services;

import br.com.moria.domains.mensalidade.Mensalidade;
import br.com.moria.domains.mensalidade.MensalidadeMapper;
import br.com.moria.domains.mensalidade.MensalidadeRepository;
import br.com.moria.domains.mensalidade.dtos.MensalidadeResponseDTO;
import br.com.moria.shared.enums.EntityType;
import br.com.moria.shared.exceptions.NotFoundResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementação do serviço para operações relacionadas a visualização de mensalidades.
 *
 * <p>Fornece funcionalidades para consulta de mensalidades, como obter dados detalhados de uma mensalidade
 * e listar mensalidades com base em diferentes critérios.</p>
 *
 * @see IMensalidadeQueryService
 */
@Service
public class MensalidadeQueryService implements IMensalidadeQueryService {

    private final MensalidadeMapper mensalidadeMapper;
    private final MensalidadeRepository mensalidadeRepository;

    /**
     * Construtor para injeção de dependências.
     *
     * @param mensalidadeMapper     o mapeador de mensalidades.
     * @param mensalidadeRepository o repositório de mensalidades.
     */
    @Autowired
    public MensalidadeQueryService(MensalidadeMapper mensalidadeMapper, MensalidadeRepository mensalidadeRepository) {
        this.mensalidadeMapper = mensalidadeMapper;
        this.mensalidadeRepository = mensalidadeRepository;
    }

    @Override
    public Mensalidade findMensalidadeById(int id) {
        return mensalidadeRepository.findById(id)
                .orElseThrow(() -> NotFoundResourceException.forEntity(EntityType.MENSALIDADE, id));
    }

    @Override
    public long count() {
        return mensalidadeRepository.count();
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
}
