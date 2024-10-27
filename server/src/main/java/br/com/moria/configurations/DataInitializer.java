package br.com.moria.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.moria.models.Evento;
import br.com.moria.models.Membro;
import br.com.moria.models.Mensalidade;
import br.com.moria.repositories.EventoRepository;
import br.com.moria.repositories.MembroRepository;
import br.com.moria.repositories.MensalidadeRepository;
import br.com.moria.services.implementations.EventoServiceImpl;
import br.com.moria.services.implementations.MembroServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private MembroRepository membroRepository;
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private MensalidadeRepository mensalidadeRepository;

    @Autowired
    private MembroServiceImpl membroService;
    @Autowired
    private EventoServiceImpl eventoService;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Override
    public void run(String... args) throws Exception {
        final String path = "src/main/resources/json/";

        if (membroRepository.count() == 0) {
            List<Membro> modelos = List.of(
                objectMapper.readValue(new File(path + "membros.json"), Membro[].class));
            for (Membro modelo : modelos) 
                membroService.create(modelo);
        }

        if (eventoRepository.count() == 0) {
            List<Evento> eventos = List.of(
                objectMapper.readValue(new File(path + "eventos.json"), Evento[].class));
            for (Evento evento : eventos)
                eventoService.create(evento);
        }

        if (mensalidadeRepository.count() == 0) {
            List<Mensalidade> mensalidades = List.of(
                objectMapper.readValue(new File(path + "mensalidades.json"), Mensalidade[].class));
            for (Mensalidade mensalidade : mensalidades)
                mensalidadeRepository.save(mensalidade); 
        }
    }
}
