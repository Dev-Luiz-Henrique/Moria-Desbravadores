package br.com.moria.services.implementations;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.moria.dtos.FileResponseDTO;
import br.com.moria.dtos.Evento.EventoCreateDTO;
import br.com.moria.dtos.Evento.EventoResponseDTO;
import br.com.moria.dtos.Evento.EventoUpdateDTO;
import br.com.moria.mappers.EventoMapper;
import br.com.moria.models.Endereco;
import br.com.moria.models.Evento;
import br.com.moria.repositories.EventoRepository;
import br.com.moria.services.interfaces.IEnderecoService;
import br.com.moria.services.interfaces.IEventoService;
import br.com.moria.services.interfaces.IFileService;
import jakarta.persistence.EntityNotFoundException;

/**
 * Implementação do serviço para operações relacionadas a eventos.
 *
 * <p>Fornece a lógica para manipulação de dados de eventos, incluindo validações,
 * criação, atualização, exclusão e gerenciamento de imagens associadas aos eventos.</p>
 *
 * @see IEventoService
 */
@Service
public class EventoServiceImpl implements IEventoService {

    private final EventoMapper eventoMapper;
	private final EventoRepository eventoRepository;
    private final IEnderecoService enderecoService;
    private final IFileService fileService;

	/**
	 * Construtor para injeção de dependências.
	 *
	 * @param eventoMapper      o mapper para conversão entre DTO e entidade.
	 * @param eventoRepository  o repositório para operações com a entidade {@link Evento}.
	 * @param enderecoService   o serviço para manipulação de endereços.
	 * @param fileService       o serviço para manipulação de arquivos.
	 */
    @Autowired
    public EventoServiceImpl(EventoMapper eventoMapper,
							 EventoRepository eventoRepository,
							 IEnderecoService enderecoService,
							 IFileService fileService) {
        this.eventoMapper = eventoMapper;
        this.eventoRepository = eventoRepository;
        this.enderecoService = enderecoService;
        this.fileService = fileService;
    }

	/**
	 * Valida o intervalo de datas de início e término.
	 *
	 * @param start a data de início.
	 * @param end a data de término.
	 * @throws IllegalArgumentException se a data de término for anterior à data de início.
	 */
    private void validateDate(LocalDateTime start, @NotNull LocalDateTime end){
        if(end.isBefore(start))
			throw new IllegalArgumentException("A data de inicio não pode suceder a data de fim.");
    }

    @Override
    public Evento findEventoById(int id) {
        return eventoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado."));
    }

	@Override
	public long count() {
		return eventoRepository.count();
	}

    @Override
    public boolean existsById(int id) {
		return eventoRepository.existsById(id);
    }

	@Override
	public EventoResponseDTO create(@NotNull EventoCreateDTO eventoCreateDTO) {
        validateDate(eventoCreateDTO.getDataInicio(), eventoCreateDTO.getDataFim());

		Endereco endereco = enderecoService.findOrCreate(eventoCreateDTO.getEnderecoCreateDTO());
        Evento evento = eventoMapper.toEntity(eventoCreateDTO);
        evento.setEndereco(endereco);
        
        Evento savedEvento = eventoRepository.save(evento);
		return eventoMapper.toResponseDTO(savedEvento);
	}

	@Override
	public EventoResponseDTO update(@NotNull EventoUpdateDTO eventoUpdateDTO) {
        findEventoById(eventoUpdateDTO.getId());
        validateDate(eventoUpdateDTO.getDataInicio(), eventoUpdateDTO.getDataFim());
	
        Endereco endereco = enderecoService.findOrCreate(eventoUpdateDTO.getEnderecoCreateDTO());
        Evento evento = eventoMapper.toEntity(eventoUpdateDTO);
        evento.setEndereco(endereco);

        Evento updatedEvento = eventoRepository.save(evento);
		return eventoMapper.toResponseDTO(updatedEvento);
	}

	@Override
	public void delete(int id) {
		Evento existingEvento = findEventoById(id);
	    eventoRepository.delete(existingEvento);
	}

    @Override
	public List<EventoResponseDTO> findAll() {
		List<Evento> eventos = eventoRepository.findAll();
		return eventoMapper.toResponseDTO(eventos);
	}

	@Override
	public EventoResponseDTO findById(int id) {
		Evento existingEvento = findEventoById(id);
		return eventoMapper.toResponseDTO(existingEvento);
	}

	@Override
	public List<EventoResponseDTO> findByNomeContaining(String keyword) {
		List<Evento> eventos = eventoRepository.findByNomeContaining(keyword);
		return eventoMapper.toResponseDTO(eventos);
	}

	@Override
	public List<EventoResponseDTO> findByDataInicioInterval(LocalDateTime start, LocalDateTime end) {
		List<Evento> eventos = eventoRepository.findByDataInicioBetween(start, end);
		return eventoMapper.toResponseDTO(eventos);
	}

	@Override
	public List<EventoResponseDTO> findByDataInicio(LocalDateTime date) {
		List<Evento> eventos = eventoRepository.findByDataInicio(date);
		return eventoMapper.toResponseDTO(eventos);
	}

	@Override
	public List<EventoResponseDTO> findByDataFim(LocalDateTime date) {
		List <Evento> eventos = eventoRepository.findByDataFim(date);
		return eventoMapper.toResponseDTO(eventos);
	}

	@Override
	public EventoResponseDTO updateImagemById(int id, MultipartFile file) throws IOException {
		Evento existingEvento = findEventoById(id);
		String filePath = fileService.uploadFile(file, "evento");
        existingEvento.setImagem(filePath);

		Evento updatedEvento = eventoRepository.save(existingEvento);
		return eventoMapper.toResponseDTO(updatedEvento);
	}

	@Override
	public FileResponseDTO findImagemById(int id) throws IOException {
		Evento existingEvento = findEventoById(id);
		String filePath = existingEvento.getImagem();
        return fileService.downloadFile(filePath);
	}
}
