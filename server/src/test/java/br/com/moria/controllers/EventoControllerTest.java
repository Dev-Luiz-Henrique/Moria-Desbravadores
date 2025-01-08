//package br.com.moria.controllers;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import br.com.moria.domain.evento.Evento;
//import br.com.moria.domain.evento.services.IEventoService;
//import jakarta.persistence.EntityNotFoundException;
//
//public class EventoControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private IEventoService eventoService;
//
//    @InjectMocks
//    private EventoController eventoController;
//
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(eventoController).build();
//        objectMapper = new ObjectMapper();
//    }
//
//    @Test
//    public void testCreate() throws Exception {
//        Evento evento = new Evento(); // Preencha com dados válidos
//        when(eventoService.create(any(Evento.class))).thenReturn(evento);
//
//        mockMvc.perform(post("/eventos")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(evento)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(evento.getId()));
//    }
//
//    @Test
//    public void testCreateBadRequest() throws Exception {
//        Evento evento = new Evento(); // Preencha com dados inválidos
//        when(eventoService.create(any(Evento.class))).thenThrow(new IllegalArgumentException("Dados inválidos"));
//
//        mockMvc.perform(post("/eventos")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(evento)))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.message").value("Dados inválidos"));
//    }
//
//    @Test
//    public void testUpdate() throws Exception {
//        Evento evento = new Evento(); // Preencha com dados válidos
//        evento.setId(1);
//        when(eventoService.update(any(Evento.class))).thenReturn(evento);
//
//        mockMvc.perform(put("/eventos/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(evento)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(evento.getId()));
//    }
//
//    @Test
//    public void testUpdateNotFound() throws Exception {
//        Evento evento = new Evento(); // Preencha com dados válidos
//        evento.setId(1);
//        when(eventoService.update(any(Evento.class))).thenThrow(new EntityNotFoundException());
//
//        mockMvc.perform(put("/eventos/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(evento)))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void testDelete() throws Exception {
//        doNothing().when(eventoService).delete(1);
//
//        mockMvc.perform(delete("/eventos/1"))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    public void testDeleteNotFound() throws Exception {
//        doThrow(new EntityNotFoundException()).when(eventoService).delete(1);
//
//        mockMvc.perform(delete("/eventos/1"))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void testFindAll() throws Exception {
//        List<Evento> eventos = new ArrayList<>();
//        when(eventoService.findAll()).thenReturn(eventos);
//
//        mockMvc.perform(get("/eventos"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$").isArray());
//    }
//
//    @Test
//    public void testFindById() throws Exception {
//        Evento evento = new Evento();
//        evento.setId(1);
//        when(eventoService.findById(1)).thenReturn(evento);
//
//        mockMvc.perform(get("/eventos/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(evento.getId()));
//    }
//
//    @Test
//    public void testFindByIdNotFound() throws Exception {
//        when(eventoService.findById(1)).thenThrow(new EntityNotFoundException());
//    }
//}
