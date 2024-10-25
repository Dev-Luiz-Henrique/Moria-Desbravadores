package br.com.moria.services.implementations;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moria.models.Evento;
import br.com.moria.models.Recurso;
import br.com.moria.repositories.EventoRepository;
import br.com.moria.repositories.RecursoRepository;
import br.com.moria.services.interfaces.IRecursoService;
import jakarta.validation.Valid;

@Service
public class RecursoServiceImpl implements IRecursoService {

	@Autowired
	private RecursoRepository recursoRepository;

	@Autowired
	private EventoRepository eventoRepository;

	@Override
	public Recurso create(@Valid Recurso recurso) {
		Evento evento = recurso.getEvento();
		recurso.setEvento(eventoRepository.findById(evento.getId())
				.orElseGet(() -> eventoRepository.save(evento)));
		
        return recursoRepository.save(recurso);
	}

	@Override
	public Recurso update(@Valid Recurso recurso) {
        if (!recursoRepository.existsById(recurso.getId())) {
			throw new IllegalArgumentException("Recurso não encontrado para o ID fornecido.");
		}
        if (!eventoRepository.existsById(recurso.getEvento().getId())) {
			throw new IllegalArgumentException("Evento não encontrado para o ID fornecido.");
		}

        Recurso recursoExistente = recursoRepository.findById(recurso.getId()).get();

        String[] ignoreProps = {"nome", "descricao", "valor", "quantidade", "formaPagamento", "categoria", "status"};

        BeanUtils.copyProperties(recurso, recursoExistente, ignoreProps);

        recursoExistente.setNome(recurso.getNome());
        recursoExistente.setDescricao(recurso.getDescricao());
        recursoExistente.setValor(recurso.getValor());
        recursoExistente.setQuantidade(recurso.getQuantidade());
        recursoExistente.setFormaPagamento(recurso.getFormaPagamento());
        recursoExistente.setCategoria(recurso.getCategoria());
        recursoExistente.setStatusPagamento(recurso.getStatusPagamento());

        return recursoRepository.save(recursoExistente);
	}

	@Override
	public void delete(int id) {
        if (!recursoRepository.existsById(id)) {
			throw new IllegalArgumentException("Recurso não encontrado para o ID fornecido.");
		}
        recursoRepository.deleteById(id);
	}

	@Override
	public List<Recurso> findAll() {
		return recursoRepository.findAll();
	}

}
