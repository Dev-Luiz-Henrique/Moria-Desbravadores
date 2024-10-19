package br.com.moria.services.implementations.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import br.com.moria.enums.EstadoCivil;
import br.com.moria.enums.TipoMembro;
import br.com.moria.models.Endereco;
import br.com.moria.models.Membro;
import br.com.moria.repositories.EnderecoRepository;
import br.com.moria.repositories.MembroRepository;
import br.com.moria.services.implementations.MembroServiceImpl;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class MembroServiceImplIntegrationTest {

    @Autowired
    private MembroRepository membroRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private MembroServiceImpl membroService;

    private Membro membro;
    private Endereco endereco;

    @BeforeEach
    void setUp() throws Exception {
        endereco = new Endereco();
        endereco.setCep("12345678");
        endereco.setCidade("Sao Paulo");
        endereco.setEstado("SP");
        endereco.setBairro("Centro");
        membro = new Membro();
        membro.setEndereco(endereco);
        membro.setNome("João Silva");
        membro.setSexo("M");
        membro.setDataNascimento(LocalDate.of(1990, 1, 1));
        membro.setTelefone("1234567890");
        membro.setCelular("98765432101");
        membro.setEmail("joao.silvaaa@example.com");
        membro.setSenha("senha123".getBytes());
        membro.setLogradouro("Rua das Flores");
        membro.setNumero(123);
        membro.setCpf("12345238901");
        membro.setRg("123456789");
        membro.setOrgaoExpedidor("SSP");
        membro.setProfissionalSaude("Médico");
        membro.setTamanhoCamisa("M");
        membro.setEstadoCivil(EstadoCivil.SOLTEIRO);
        membro.setBatizado(true);
        membro.setDataCadastro(LocalDateTime.now());
        membro.setAtivo(true);
        membro.setFichaSaude("Sem restrições.");
        membro.setTipo(TipoMembro.DESBRAVADOR);
    }

    @Test
    void testCreateMembro() {
        Membro result = membroService.create(membro);
        Membro savedMembro = membroRepository.findById(result.getId()).orElse(null);
        Endereco savedEndereco = enderecoRepository.findById(result.getEndereco().getId()).orElse(null);

        assertThat(savedMembro).isNotNull();
        assertThat(savedEndereco).isNotNull();
        assertThat(savedMembro.getEndereco().getCep()).isEqualTo("12345678");
    }
}
