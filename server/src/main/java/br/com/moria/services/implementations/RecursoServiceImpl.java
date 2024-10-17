package br.com.moria.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moria.models.Recurso;
import br.com.moria.repositories.EventoRepository;
import br.com.moria.repositories.RecursoRepository;
import br.com.moria.services.interfaces.IRecursoService;

@Service
public class RecursoServiceImpl implements IRecursoService {

	@Autowired
	private RecursoRepository recursoRepository;

	@Autowired
	private EventoRepository eventoRepository;

	@Override
	public Recurso create(Recurso recurso) {

		return null;
	}

	@Override
	public Recurso update(Recurso recurso) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
