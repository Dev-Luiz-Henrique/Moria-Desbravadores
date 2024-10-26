package br.com.moria.services.implementations.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import br.com.moria.models.Endereco;
import br.com.moria.models.Membro;
import br.com.moria.repositories.EnderecoRepository;
import br.com.moria.repositories.MembroRepository;
import br.com.moria.services.implementations.MembroServiceImpl;
import br.com.moria.services.interfaces.IFileService;

import java.io.IOException;
import java.util.Optional;

@SpringBootTest
public class MembroServiceTest {

    @Mock
    private MembroRepository membroRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private IFileService uploadService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private MembroServiceImpl membroService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateMembro() {
        Membro membro = new Membro();
        membro.setEmail("email@example.com");
        membro.setCpf("12345678901");
        membro.setSenha("password");
        Endereco endereco = new Endereco();
        membro.setEndereco(endereco);

        when(membroRepository.findByEmail(any(String.class))).thenReturn(null);
        when(membroRepository.findByCpf(any(String.class))).thenReturn(null);
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");
        when(enderecoRepository.findByCep(any(String.class))).thenReturn(Optional.of(endereco));
        when(membroRepository.save(any(Membro.class))).thenReturn(membro);

        Membro createdMembro = membroService.create(membro);

        assertNotNull(createdMembro);
        assertEquals("encodedPassword", createdMembro.getSenha());
    }

    @Test
    public void testFindById() {
        Membro membro = new Membro();
        when(membroRepository.findById(anyInt())).thenReturn(Optional.of(membro));

        Membro foundMembro = membroService.findById(1);

        assertNotNull(foundMembro);
    }

    @Test
    public void testDelete() {
        Membro membro = new Membro();
        when(membroRepository.findById(anyInt())).thenReturn(Optional.of(membro));

        membroService.delete(1);

        verify(membroRepository, times(1)).delete(membro);
    }

    @Test
    public void testUpdateFichaSaudeById() throws IOException {
        Membro membro = new Membro();
        MultipartFile file = mock(MultipartFile.class);
        when(membroRepository.findById(anyInt())).thenReturn(Optional.of(membro));
        when(uploadService.uploadFichaSaude(any(MultipartFile.class))).thenReturn("filePath");
        when(membroRepository.save(any(Membro.class))).thenReturn(membro);

        Membro updatedMembro = membroService.updateFichaSaudeById(1, file);

        assertEquals("filePath", updatedMembro.getFichaSaude());
    }
}