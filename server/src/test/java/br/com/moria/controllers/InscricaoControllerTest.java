//package br.com.moria.controllers;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import br.com.moria.enums.StatusParticipacao;
//import br.com.moria.models.Inscricao;
//import br.com.moria.services.interfaces.IInscricaoService;
//
//public class InscricaoControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private IInscricaoService inscricaoService;
//
//    @InjectMocks
//    private InscricaoController inscricaoController;
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//        this.mockMvc = standaloneSetup(inscricaoController).build();
//    }
//
//    @Test
//    public void testCreate() throws Exception {
//        Inscricao inscricao = new Inscricao();
//        inscricao.setStatusParticipacao(StatusParticipacao.AUSENTE);
//
//        when(inscricaoService.create(any(Inscricao.class))).thenReturn(inscricao);
//
//        mockMvc.perform(post("/inscricoes")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(objectMapper.writeValueAsString(inscricao)))
//            .andExpect(status().isCreated())
//            .andExpect(jsonPath("$.nome").value("Teste"));
//
//        verify(inscricaoService, times(1)).create(any(Inscricao.class));
//    }
///*
//    @Test
//    public void testUpdateStatusParticipacao() throws Exception {
//        Inscricao inscricao = new Inscricao();
//        inscricao.setStatusParticipacao(StatusParticipacao.PRESENTE);
//
//        when(inscricaoService.updateStatusParticipacao(anyInt(), any(StatusParticipacao.class))).thenReturn(inscricao);
//
//        mockMvc.perform(put("/inscricoes/1/status")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(objectMapper.writeValueAsString(inscricao.getStatusParticipacao())))
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$.statusParticipacao").value("CONFIRMADO"));
//
//        verify(inscricaoService, times(1)).updateStatusParticipacao(anyInt(), any(StatusParticipacao.class));
//    }
//
//    @Test
//    public void testUploadAutorizacaoResponsavel() throws Exception {
//        MockMultipartFile file = new MockMultipartFile("file", "test.txt", MediaType.TEXT_PLAIN_VALUE, "Test".getBytes());
//
//        Inscricao inscricao = new Inscricao();
//        inscricao.setAutorizacao("path/to/test.txt");
//
//        when(inscricaoService.updateAutorizacaoResponsavel(anyInt(), any(MultipartFile.class))).thenReturn(inscricao);
//
//        mockMvc.perform(multipart("/inscricoes/1/autorizacao").file(file))
//            .andExpect(status().isOk())
//            .andExpect(content().string("Autorização do Responsável salva em: path/to/test.txt"));
//
//        verify(inscricaoService, times(1)).updateAutorizacaoResponsavel(anyInt(), any(MultipartFile.class));
//    }
//*/
//    @Test
//    public void testDelete() throws Exception {
//        doNothing().when(inscricaoService).delete(anyInt());
//
//        mockMvc.perform(delete("/inscricoes/1"))
//            .andExpect(status().isNoContent());
//
//        verify(inscricaoService, times(1)).delete(anyInt());
//    }
//
//    @Test
//    public void testFindAll() throws Exception {
//        Inscricao inscricao1 = new Inscricao();
//
//        Inscricao inscricao2 = new Inscricao();
//
//        List<Inscricao> inscricoes = Arrays.asList(inscricao1, inscricao2);
//
//        when(inscricaoService.findAll()).thenReturn(inscricoes);
//
//        mockMvc.perform(get("/inscricoes"))
//            .andExpect(status().isOk());
//
//
//        verify(inscricaoService, times(1)).findAll();
//    }
///*
//    @Test
//    public void testFindStatusParticipacao() throws Exception {
//        Inscricao inscricao1 = new Inscricao();
//        inscricao1.setStatusParticipacao(StatusParticipacao.AUSENTE);
//        Inscricao inscricao2 = new Inscricao();
//        inscricao2.setStatusParticipacao(StatusParticipacao.JUSTIFICADO);
//        List<Inscricao> inscricoes = Arrays.asList(inscricao1, inscricao2);
//
//        when(inscricaoService.findStatusParticipacao(any(StatusParticipacao.class)))
//            .thenReturn(inscricoes);
//
//        mockMvc.perform(get("/inscricoes/status").param("status", "AUSENTE"))
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$[0].statusParticipacao").value("AUSENTE"))
//            .andExpect(jsonPath("$[1].statusParticipacao").value("JUSTIFICADO"));
//
//        verify(inscricaoService, times(1)).findStatusParticipacao(any(StatusParticipacao.class));
//    }
//*/
//}
//
