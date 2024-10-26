package br.com.moria.services.interfaces;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.moria.dtos.FileResponseDTO;
import br.com.moria.models.Evento;

public interface IEventoService {
	public Evento create(Evento evento);
	public Evento update(Evento evento);
	public void delete(int id);
	public Evento findById(int id);
	public Evento updateImagemEventoById(int id, MultipartFile file) throws IOException;
	public FileResponseDTO getImagemEventoById(int id) throws IOException;
	public List<Evento> findAll();
	public List<Evento> findNomeContaining(String keyword);
	public List<Evento> findDataInicio(LocalDateTime date);
	public List<Evento> findDataFim(LocalDateTime date);
	public List<Evento> findDataInicioInterval(LocalDateTime start, LocalDateTime end);
}
