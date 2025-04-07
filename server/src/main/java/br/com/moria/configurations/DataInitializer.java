package br.com.moria.configurations;

import br.com.moria.domains.evento.services.IEventoCommandService;
import br.com.moria.domains.evento.services.IEventoQueryService;
import br.com.moria.domains.inscricao.services.IInscricaoCommandService;
import br.com.moria.domains.membro.services.IMembroCommandService;
import br.com.moria.domains.mensalidade.services.IMensalidadeCommandService;
import br.com.moria.domains.mensalidade.services.IMensalidadeQueryService;
import br.com.moria.domains.recurso.services.IRecursoCommandService;
import br.com.moria.domains.recurso.services.IRecursoQueryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.moria.domains.inscricao.services.IInscricaoQueryService;
import br.com.moria.domains.membro.services.IMembroQueryService;

import java.io.InputStream;
import java.util.List;
import java.util.function.Consumer;

@Component
@Profile("dev")
public class DataInitializer implements CommandLineRunner {

    private final IMembroCommandService membroCommandService;
    private final IMembroQueryService membroQueryService;
    private final IEventoCommandService eventoCommandService;
    private final IEventoQueryService eventoQueryService;
    private final IRecursoCommandService recursoCommandService;
    private final IRecursoQueryService recursoQueryService;
    private final IInscricaoCommandService inscricaoCommandService;
    private final IInscricaoQueryService inscricaoQueryService;
    private final IMensalidadeCommandService mensalidadeCommandService;
    private final IMensalidadeQueryService mensalidadeQueryService;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private static final String JSON_PATH = "json/";

    @Autowired
    public DataInitializer(
            IMembroCommandService membroCommandService,
            IMembroQueryService membroQueryService,
            IEventoCommandService eventoCommandService,
            IEventoQueryService eventoQueryService,
            IRecursoCommandService recursoCommandService,
            IRecursoQueryService recursoQueryService,
            IInscricaoCommandService inscricaoCommandService,
            IInscricaoQueryService inscricaoQueryService,
            IMensalidadeCommandService mensalidadeCommandService,
            IMensalidadeQueryService  mensalidadeQueryService
    ) {
        this.membroCommandService = membroCommandService;
        this.membroQueryService = membroQueryService;
        this.eventoCommandService = eventoCommandService;
        this.eventoQueryService = eventoQueryService;
        this.recursoCommandService = recursoCommandService;
        this.recursoQueryService = recursoQueryService;
        this.inscricaoCommandService = inscricaoCommandService;
        this.inscricaoQueryService = inscricaoQueryService;
        this.mensalidadeCommandService = mensalidadeCommandService;
        this.mensalidadeQueryService = mensalidadeQueryService;
    }

    @Override
    public void run(String... args) throws Exception {
        Thread.sleep(3000);
        loadData("membros.json", MembroCreateDTO[].class,
                membroQueryService.count(), membroCommandService::create);
        loadData("eventos.json", EventoCreateDTO[].class,
                eventoQueryService.count(), eventoCommandService::create);
        loadData("recursos.json", RecursoCreateDTO[].class,
                recursoQueryService.count(), recursoCommandService::create);
        loadData("inscricoes.json", InscricaoCreateDTO[].class,
                inscricaoQueryService.count(), inscricaoCommandService::create);
        loadData("mensalidades.json", MensalidadeCreateDTO[].class,
                mensalidadeQueryService.count(), mensalidadeCommandService::create);
    }

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
