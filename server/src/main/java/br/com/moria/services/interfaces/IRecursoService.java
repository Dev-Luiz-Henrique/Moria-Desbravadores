package br.com.moria.services.interfaces;

import br.com.moria.models.Recurso;

public interface IRecursoService {
	public Recurso create(Recurso recurso);
    public Recurso update(Recurso recurso);
    public void delete(int id);
}
