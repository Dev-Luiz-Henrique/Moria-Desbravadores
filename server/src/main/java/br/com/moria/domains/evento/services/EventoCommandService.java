package br.com.moria.domains.evento.services;

import br.com.moria.domains.endereco.Endereco;
import br.com.moria.domains.endereco.services.IEnderecoService;
import br.com.moria.domains.evento.Evento;
import br.com.moria.domains.evento.EventoMapper;
import br.com.moria.domains.evento.EventoRepository;
import br.com.moria.domains.evento.dtos.EventoCreateDTO;
import br.com.moria.domains.evento.dtos.EventoResponseDTO;
import br.com.moria.domains.evento.dtos.EventoUpdateDTO;
import br.com.moria.domains.file.FileResponseDTO;
import br.com.moria.domains.file.IFileService;
import br.com.moria.shared.enums.EntityType;
import br.com.moria.shared.exceptions.NotFoundResourceException;
import br.com.moria.shared.exceptions.ValidationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class EventoCommandService implements IEventoCommandService {

    private final EventoRepository eventoRepository;
    private final EventoMapper eventoMapper;
    private final IEventoQueryService eventoQueryService;
    private final IEnderecoService enderecoService;
    private final IFileService fileService;

    @Autowired
    public EventoCommandService(EventoRepository eventoRepository,
                                EventoMapper eventoMapper,
                                IEventoQueryService eventoQueryService,
                                IEnderecoService enderecoService,
                                IFileService fileService) {
        this.eventoRepository = eventoRepository;
        this.eventoMapper = eventoMapper;
        this.enderecoService = enderecoService;
        this.eventoQueryService = eventoQueryService;
        this.fileService = fileService;
    }

    private void validateDate(LocalDateTime start, @NotNull LocalDateTime end) {
        if (end.isBefore(start))
            throw ValidationException.of("business.evento.data.invalid");
    }

    @Override
    public EventoResponseDTO create(@NotNull EventoCreateDTO eventoCreateDTO) {
        validateDate(eventoCreateDTO.getDataInicio(), eventoCreateDTO.getDataFim());

        Endereco endereco = enderecoService.findOrCreate(eventoCreateDTO.getEnderecoCreateDTO());
        Evento evento = eventoMapper.toEntity(eventoCreateDTO);
        evento.setEndereco(endereco);

        return eventoMapper.toResponseDTO(eventoRepository.save(evento));
    }

    @Override
    public EventoResponseDTO update(@NotNull EventoUpdateDTO eventoUpdateDTO) {
        eventoQueryService.findById(eventoUpdateDTO.getId());
        validateDate(eventoUpdateDTO.getDataInicio(), eventoUpdateDTO.getDataFim());

        Endereco endereco = enderecoService.findOrCreate(eventoUpdateDTO.getEnderecoCreateDTO());
        Evento evento = eventoMapper.toEntity(eventoUpdateDTO);
        evento.setEndereco(endereco);

        return eventoMapper.toResponseDTO(eventoRepository.save(evento));
    }

    @Override
    public void delete(int id) {
        Evento existingEvento = eventoRepository.findById(id)
                .orElseThrow(() -> NotFoundResourceException.forEntity(EntityType.EVENTO, id));
        eventoRepository.delete(existingEvento);
    }

    @Override
    public EventoResponseDTO updateImagemById(int id, MultipartFile file) throws IOException {
        Evento existingEvento = eventoQueryService.findEventoById(id);
        String filePath = fileService.uploadFile(file, "evento");
        existingEvento.setImagem(filePath);

        Evento updatedEvento = eventoRepository.save(existingEvento);
        return eventoMapper.toResponseDTO(updatedEvento);
    }

    @Override
    public FileResponseDTO findImagemById(int id) throws IOException {
        Evento existingEvento = eventoQueryService.findEventoById(id);
        String filePath = existingEvento.getImagem();
        return fileService.downloadFile(filePath);
    }
}
