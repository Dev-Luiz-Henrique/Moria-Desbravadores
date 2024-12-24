package br.com.moria.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.com.moria.dtos.Evento.EventoCreateDTO;
import br.com.moria.dtos.Inscricao.InscricaoCreateDTO;
import br.com.moria.dtos.Membro.MembroCreateDTO;
import br.com.moria.dtos.Mensalidade.MensalidadeCreateDTO;
import br.com.moria.dtos.Recurso.RecursoCreateDTO;
import br.com.moria.services.interfaces.*;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Component
@Profile("dev")
public class DataInitializer implements CommandLineRunner {

    private final IMembroService membroService;
    private final IEventoService eventoService;
    private final IRecursoService recursoService;
    private final IInscricaoService inscricaoService;
    private final IMensalidadeService mensalidadeService;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private static final String JSON_PATH = "src/main/resources/json/";

    public DataInitializer(
            IMembroService membroService,
            IEventoService eventoService,
            IRecursoService recursoService,
            IInscricaoService inscricaoService,
            IMensalidadeService mensalidadeService
    ) {
        this.membroService = membroService;
        this.eventoService = eventoService;
        this.recursoService = recursoService;
        this.inscricaoService = inscricaoService;
        this.mensalidadeService = mensalidadeService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData("membros.json", MembroCreateDTO[].class,
                membroService.count(), membroService::create);
        loadData("eventos.json", EventoCreateDTO[].class,
                eventoService.count(), eventoService::create);
        loadData("mensalidades.json", MensalidadeCreateDTO[].class,
                mensalidadeService.count(), mensalidadeService::create);
        loadData("inscricoes.json", InscricaoCreateDTO[].class,
                inscricaoService.count(), inscricaoService::create);
        loadData("recursos.json", RecursoCreateDTO[].class,
                recursoService.count(), recursoService::create);
    }

    private <T> void loadData(String fileName,
                              Class<T[]> clazz,
                              long count,
                              Consumer<T> saveFn) throws Exception {
        if (count == 0) {
            File file = new File(JSON_PATH + fileName);
            List<T> data = List.of(objectMapper.readValue(file, clazz));
            data.forEach(saveFn);
            System.out.println(data.size() + " registros inseridos de " + clazz.getSimpleName());
        }
    }
}
