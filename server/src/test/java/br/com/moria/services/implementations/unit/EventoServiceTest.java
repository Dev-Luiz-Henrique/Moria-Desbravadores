package br.com.moria.services.implementations.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.moria.models.Endereco;
import br.com.moria.models.Evento;
import br.com.moria.repositories.EnderecoRepository;
import br.com.moria.repositories.EventoRepository;
import br.com.moria.services.implementations.EventoServiceImpl;

@SpringBootTest
public class EventoServiceTest {

    @Mock
    private EventoRepository eventoRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EventoServiceImpl eventoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateEvento() {
        Evento evento = new Evento();
        Endereco endereco = new Endereco();
        evento.setEndereco(endereco);
        evento.setDataInicio(LocalDateTime.now().plusDays(1));
        evento.setDataFim(LocalDateTime.now().plusDays(2));

        when(enderecoRepository.findByCep(anyString())).thenReturn(Optional.of(endereco));
        when(eventoRepository.save(any(Evento.class))).thenReturn(evento);

        Evento createdEvento = eventoService.create(evento);

        assertNotNull(createdEvento);
    }

    @Test
    public void testCreateEventoDataFimAntesDataInicio() {
        Evento evento = new Evento();
        evento.setDataInicio(LocalDateTime.now().plusDays(2));
        evento.setDataFim(LocalDateTime.now().plusDays(1));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            eventoService.create(evento);
        });

        assertEquals("Data inicio não pode suceder data fim.", exception.getMessage());
    }

    @Test
    public void testFindById() {
        Evento evento = new Evento();
        when(eventoRepository.findById(anyInt())).thenReturn(Optional.of(evento));

        Evento foundEvento = eventoService.findById(1);

        assertNotNull(foundEvento);
    }

    @Test
    public void testDeleteEvento() {
        Evento evento = new Evento();
        when(eventoRepository.findById(anyInt())).thenReturn(Optional.of(evento));

        eventoService.delete(1);

        verify(eventoRepository, times(1)).delete(evento);
    }

    @Test
    public void testFindNomeContaining() {
        Evento evento1 = new Evento();
        evento1.setNome("Evento A");
        Evento evento2 = new Evento();
        evento2.setNome("Evento B");

        List<Evento> eventos = Arrays.asList(evento1, evento2);

        when(eventoRepository.findByNomeContaining(anyString())).thenReturn(eventos);

        List<Evento> result = eventoService.findNomeContaining("Evento");

        assertEquals(2, result.size());
    }

    @Test
    public void testFindDataInicioInterval() {
        Evento evento1 = new Evento();
        evento1.setDataInicio(LocalDateTime.now().plusDays(1));
        Evento evento2 = new Evento();
        evento2.setDataInicio(LocalDateTime.now().plusDays(2));

        List<Evento> eventos = Arrays.asList(evento1, evento2);

        when(eventoRepository.findByDataInicioBetween(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(eventos);

        List<Evento> result = eventoService.findDataInicioInterval(LocalDateTime.now(), LocalDateTime.now().plusDays(3));

        assertEquals(2, result.size());
    }
}
