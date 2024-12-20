package br.com.moria.services.interfaces;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.moria.dtos.FileResponseDTO;
import br.com.moria.dtos.Evento.EventoCreateDTO;
import br.com.moria.dtos.Evento.EventoResponseDTO;
import br.com.moria.dtos.Evento.EventoUpdateDTO;

public interface IEventoService {
	EventoResponseDTO create(EventoCreateDTO eventoCreateDTO);
	EventoResponseDTO update(EventoUpdateDTO eventoUpdateDTO);
	void delete(int id);
	EventoResponseDTO findById(int id);
	List<EventoResponseDTO> findAll();
	List<EventoResponseDTO> findByNomeContaining(String keyword);
	List<EventoResponseDTO> findByDataInicio(LocalDateTime date);
	List<EventoResponseDTO> findByDataFim(LocalDateTime date);
	List<EventoResponseDTO> findByDataInicioInterval(LocalDateTime start, LocalDateTime end);
    EventoResponseDTO updateImagemById(int id, MultipartFile file) throws IOException;
	FileResponseDTO findImagemById(int id) throws IOException;
}