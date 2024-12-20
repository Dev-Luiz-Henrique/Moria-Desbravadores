package br.com.moria.services.interfaces;

import java.util.List;

import br.com.moria.dtos.Inscricao.InscricaoCreateDTO;
import br.com.moria.dtos.Inscricao.InscricaoResponseDTO;
import br.com.moria.dtos.Inscricao.InscricaoUpdateDTO;
import br.com.moria.enums.StatusParticipacao;

public interface IInscricaoService {
	InscricaoResponseDTO create(InscricaoCreateDTO InscricaoCreateDTO);
	InscricaoResponseDTO update(InscricaoUpdateDTO inscricao);
	InscricaoResponseDTO updateStatusInscricao(int membroId,int eventoId);
	void delete(int id);
	List<InscricaoResponseDTO> findAll();
    InscricaoResponseDTO findById(int id);
	List<InscricaoResponseDTO> findByStatusParticipacao(StatusParticipacao status);
    List<InscricaoResponseDTO> findInscricoesByEventoId(int eventoId);
    boolean isInscrito(int id);
}
