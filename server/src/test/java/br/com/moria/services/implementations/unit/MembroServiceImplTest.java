package br.com.moria.services.implementations.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.moria.models.Endereco;
import br.com.moria.models.Membro;
import br.com.moria.repositories.EnderecoRepository;
import br.com.moria.repositories.MembroRepository;
import br.com.moria.services.implementations.MembroServiceImpl;

public class MembroServiceImplTest {

    @Mock
    private MembroRepository membroRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private MembroServiceImpl membroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateMembroWithNewEndereco() {
        Endereco endereco = new Endereco();
        endereco.setCep("12345678");

        Membro membro = new Membro();
        membro.setEndereco(endereco);

        when(enderecoRepository.findByCep("12345678")).thenReturn(Optional.empty());
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);
        when(membroRepository.save(any(Membro.class))).thenReturn(membro);

        Membro result = membroService.create(membro);
        verify(enderecoRepository, times(1)).findByCep("12345678");
        verify(enderecoRepository, times(1)).save(endereco);
        verify(membroRepository, times(1)).save(membro);

        assertThat(result).isNotNull();
        assertThat(result.getEndereco().getCep()).isEqualTo("12345678");
    }

    @Test
    void shouldCreateMembroWithExistingEndereco() {
        Endereco endereco = new Endereco();
        endereco.setCep("12345678");

        Membro membro = new Membro();
        membro.setEndereco(endereco);

        when(enderecoRepository.findByCep("12345678")).thenReturn(Optional.of(endereco));
        when(membroRepository.save(any(Membro.class))).thenReturn(membro);

        Membro result = membroService.create(membro);

        verify(enderecoRepository, times(1)).findByCep("12345678");
        verify(enderecoRepository, times(0)).save(any(Endereco.class));
        verify(membroRepository, times(1)).save(membro);

        assertThat(result).isNotNull();
        assertThat(result.getEndereco().getCep()).isEqualTo("12345678");
    }
}