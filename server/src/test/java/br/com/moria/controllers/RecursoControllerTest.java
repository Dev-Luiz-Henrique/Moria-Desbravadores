package br.com.moria.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.moria.models.Recurso;
import br.com.moria.services.interfaces.IRecursoService;

public class RecursoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IRecursoService recursoService;

    @InjectMocks
    private RecursoController recursoController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
    	MockitoAnnotations.openMocks(this);
        this.mockMvc = standaloneSetup(recursoController).build();
    }

    @Test
    public void testCreate() throws Exception {
        Recurso recurso = new Recurso();
        recurso.setNome("Teste");

        when(recursoService.create(any(Recurso.class))).thenReturn(recurso);

        mockMvc.perform(post("/recursos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(recurso)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.nome").value("Teste"));

        verify(recursoService, times(1)).create(any(Recurso.class));
    }

    @Test
    public void testUpdate() throws Exception {
        Recurso recurso = new Recurso();
        recurso.setNome("Teste Atualizado");

        when(recursoService.update(any(Recurso.class))).thenReturn(recurso);

        mockMvc.perform(put("/recursos/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(recurso)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nome").value("Teste Atualizado"));

        verify(recursoService, times(1)).update(any(Recurso.class));
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(recursoService).delete(1);

        mockMvc.perform(delete("/recursos/1"))
            .andExpect(status().isNoContent());

        verify(recursoService, times(1)).delete(1);
    }

    @Test
    public void testFindAll() throws Exception {
        Recurso recurso1 = new Recurso();
        recurso1.setNome("Recurso 1");
        Recurso recurso2 = new Recurso();
        recurso2.setNome("Recurso 2");

        when(recursoService.findAll()).thenReturn(Arrays.asList(recurso1, recurso2));

        mockMvc.perform(get("/recursos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].nome").value("Recurso 1"))
            .andExpect(jsonPath("$[1].nome").value("Recurso 2"));

        verify(recursoService, times(1)).findAll();
    }
}
