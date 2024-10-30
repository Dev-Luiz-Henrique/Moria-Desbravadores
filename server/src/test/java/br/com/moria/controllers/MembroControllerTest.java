//package br.com.moria.controllers;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import br.com.moria.models.Membro;
//import br.com.moria.services.interfaces.IMembroService;
//
//@WebMvcTest(MembroController.class)
//public class MembroControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private IMembroService membroService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testCreateMembro() throws Exception {
//        Membro membro = new Membro();
//        membro.setId(1);
//        membro.setNome("João");
//
//        when(membroService.create(any(Membro.class))).thenReturn(membro);
//
//        mockMvc.perform(post("/membros")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(objectMapper.writeValueAsString(membro)))
//            .andExpect(status().isCreated())
//            .andExpect(jsonPath("$.id").value(1))
//            .andExpect(jsonPath("$.nome").value("João"));
//    }
//
//    @Test
//    public void testUpdateMembro() throws Exception {
//        Membro membro = new Membro();
//        membro.setId(1);
//        membro.setNome("João");
//
//        when(membroService.update(any(Membro.class))).thenReturn(membro);
//
//        mockMvc.perform(put("/membros/1")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(objectMapper.writeValueAsString(membro)))
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$.id").value(1))
//            .andExpect(jsonPath("$.nome").value("João"));
//    }
//
//    @Test
//    public void testDeleteMembro() throws Exception {
//        doNothing().when(membroService).delete(anyInt());
//
//        mockMvc.perform(delete("/membros/1"))
//            .andExpect(status().isNoContent());
//    }
//
//    @Test
//    public void testFindAllMembros() throws Exception {
//        Membro membro1 = new Membro();
//        membro1.setId(1);
//        membro1.setNome("João");
//
//        Membro membro2 = new Membro();
//        membro2.setId(2);
//        membro2.setNome("Maria");
//
//        List<Membro> membros = Arrays.asList(membro1, membro2);
//
//        when(membroService.findAll()).thenReturn(membros);
//
//        mockMvc.perform(get("/membros"))
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$.length()").value(2))
//            .andExpect(jsonPath("$[0].id").value(1))
//            .andExpect(jsonPath("$[0].nome").value("João"))
//            .andExpect(jsonPath("$[1].id").value(2))
//            .andExpect(jsonPath("$[1].nome").value("Maria"));
//    }
//
//    @Test
//    public void testFindMembroById() throws Exception {
//        Membro membro = new Membro();
//        membro.setId(1);
//        membro.setNome("João");
//
//        when(membroService.findById(anyInt())).thenReturn(membro);
//
//        mockMvc.perform(get("/membros/1"))
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$.id").value(1))
//            .andExpect(jsonPath("$.nome").value("João"));
//    }
//
//    @Test
//    public void testUploadFichaSaude() throws Exception {
//        Membro membro = new Membro();
//        membro.setFichaSaude("path/to/ficha");
//
//        MockMultipartFile file = new MockMultipartFile("file", "ficha.pdf", "application/pdf", "Conteúdo da ficha".getBytes());
//
//        when(membroService.updateFichaSaudeById(anyInt(), any(MultipartFile.class))).thenReturn(membro);
//
//        mockMvc.perform(multipart("/membros/1/ficha-saude")
//            .file(file))
//            .andExpect(status().isOk())
//            .andExpect(content().string("Ficha de saúde salva em: path/to/ficha"));
//    }
//}
