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
		Endereco enderecoExistente = enderecoRepository.findByCep(endereco.getCep()).orElse(null);

		if (enderecoExistente != null)
		    evento.setEndereco(enderecoExistente);
		else
		    evento.setEndereco(enderecoRepository.save(endereco));
		
		return eventoRepository.save(evento);
	}

	@Override
	public Evento update(@Valid Evento evento) {
		Endereco endereco = evento.getEndereco();
		Endereco enderecoExistente = enderecoRepository.findByCep(endereco.getCep()).orElse(null);

		if (enderecoExistente != null)
		    evento.setEndereco(enderecoExistente);
		else
		    evento.setEndereco(enderecoRepository.save(endereco));
		
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

	@Override
	public List<Evento> findData(LocalDateTime date) {
		return eventoRepository.findByData(date);
	}

	@Override
	public List<Evento> findDataInterval(LocalDateTime start, LocalDateTime end) {
		return eventoRepository.findByDataBetween(start, end);
	}

}
