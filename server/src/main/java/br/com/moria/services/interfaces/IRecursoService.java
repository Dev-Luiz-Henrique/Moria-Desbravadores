package br.com.moria.services.interfaces;

import java.util.List;

import br.com.moria.dtos.Recurso.RecursoCreateDTO;
import br.com.moria.dtos.Recurso.RecursoResponseDTO;
import br.com.moria.dtos.Recurso.RecursoUpdateDTO;

public interface IRecursoService {
	RecursoResponseDTO create(RecursoCreateDTO recursoCreateDTO);
    RecursoResponseDTO update(RecursoUpdateDTO recursoUpdateDTO);
    void delete(int id);
    RecursoResponseDTO findById(int id);
    List<RecursoResponseDTO> findAll();
    List<RecursoResponseDTO> findRecursosByEventoId(int eventoId);
}
