package br.com.moria.services.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import br.com.moria.models.Evento;

public interface IEventoService {
	public Evento create(Evento evento);
	public Evento update(Evento evento);
	public void delete(int id);
	public Evento findById(int id);
	public List<Evento> findAll();
	public List<Evento> findNomeContaining(String keyword);
	public List<Evento> findData(LocalDateTime date);
	public List<Evento> findDataInterval(LocalDateTime start, LocalDateTime end);
}
