package br.com.moria.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import br.com.moria.domains.evento.dtos.EventoCreateDTO;
import br.com.moria.domains.inscricao.dtos.InscricaoCreateDTO;
import br.com.moria.domains.membro.dtos.MembroCreateDTO;
import br.com.moria.domains.mensalidade.dtos.MensalidadeCreateDTO;
import br.com.moria.domains.recurso.dtos.RecursoCreateDTO;
import br.com.moria.domains.evento.services.IEventoService;
import br.com.moria.domains.inscricao.services.IInscricaoService;
import br.com.moria.domains.membro.services.IMembroService;
import br.com.moria.domains.mensalidade.services.IMensalidadeService;
import br.com.moria.domains.recurso.services.IRecursoService;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.function.Consumer;

@Component
@Profile("dev")
public class DataInitializer implements CommandLineRunner {

    private final IMembroService membroService;
    private final IEventoService eventoService;
    private final IRecursoService recursoService;
    private final IInscricaoService inscricaoService;
    private final IMensalidadeService mensalidadeService;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private static final String JSON_PATH = "json/";

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
        Thread.sleep(3000);
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

    /*private <T> void loadData(String fileName, Class<T[]> clazz, long count, Consumer<T> saveFn) {
        try {
            if (count == 0) {
                Resource resource = new ClassPathResource("json/" + fileName);
                File file = resource.getFile();

                if (!file.exists()) {
                    System.err.println("Arquivo não encontrado: " + file.getAbsolutePath());
                    return;
                }
                List<T> data = List.of(objectMapper.readValue(file, clazz));
                data.forEach(saveFn);
                System.out.println(data.size() + " registros inseridos de " + clazz.getSimpleName());
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar " + fileName + ": " + e.getMessage());
            e.printStackTrace();
        }
    }*/

    private <T> void loadData(String fileName, Class<T[]> clazz, long count, Consumer<T> saveFn) {
        if (count > 0) {
            System.out.println("Dados já existem para: " + clazz.getSimpleName());
            return;
        }

        try {
            Resource resource = new ClassPathResource(JSON_PATH + fileName);
            try (InputStream inputStream = resource.getInputStream()) {
                List<T> data = List.of(objectMapper.readValue(inputStream, clazz));
                data.forEach(saveFn);
                System.out.println(data.size() + " registros inseridos de " + clazz.getSimpleName());
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar " + fileName + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
