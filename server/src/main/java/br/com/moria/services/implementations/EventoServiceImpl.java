package br.com.moria.services.implementations;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moria.models.Endereco;
import br.com.moria.models.Evento;
import br.com.moria.repositories.EnderecoRepository;
import br.com.moria.repositories.EventoRepository;
import br.com.moria.services.interfaces.IEventoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class EventoServiceImpl implements IEventoService {

	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Override
	public Evento create(@Valid Evento evento) {
		Endereco endereco = evento.getEndereco();
		evento.setEndereco(enderecoRepository.findByCep(endereco.getCep())
	            .orElseGet(() -> enderecoRepository.save(endereco)));
		
		LocalDateTime inicio = evento.getDataInicio();
		LocalDateTime fim = evento.getDataFim();
		
		if(fim.isBefore(inicio))
			throw new IllegalArgumentException("Data inicio não pode suceder data fim.");
		
		return eventoRepository.save(evento);
	}

	@Override
	public Evento update(@Valid Evento evento) {
		Endereco endereco = evento.getEndereco();
		evento.setEndereco(enderecoRepository.findByCep(endereco.getCep())
	            .orElseGet(() -> enderecoRepository.save(endereco)));
		
		LocalDateTime inicio = evento.getDataInicio();
		LocalDateTime fim = evento.getDataFim();
		
		if(fim.isBefore(inicio))
			throw new IllegalArgumentException("Data inicio não pode suceder data fim.");
		
		return eventoRepository.save(evento);
	}

	@Override
	public void delete(int id) {
		Evento eventoExistente = eventoRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado"));

	   eventoRepository.delete(eventoExistente);
	}
	
	@Override
	public Evento findById(int id) {
		return eventoRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado"));
	}

	@Override
	public List<Evento> findAll() {
		return eventoRepository.findAll();
	}

	@Override
	public List<Evento> findNomeContaining(String keyword) {
		return eventoRepository.findByNomeContaining(keyword);
	}

	public List<Evento> findDataInicioInterval(LocalDateTime start, LocalDateTime end) {
		return eventoRepository.findByDataInicioBetween(start, end);
	}

	@Override
	public List<Evento> findDataInicio(LocalDateTime date) {
		return eventoRepository.findByDataInicio(date);
	}

	@Override
	public List<Evento> findDataFim(LocalDateTime date) {
		return eventoRepository.findByDataFim(date);
	}

	

}
