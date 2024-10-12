package br.com.moria.services;

import br.com.moria.enums.EstadoCivil;
import br.com.moria.enums.TipoMembro;
import br.com.moria.models.Endereco;
import br.com.moria.models.Membro;
import br.com.moria.services.implementations.MembroServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class MembroTest {

    @Autowired
    private MembroServiceImpl service;

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
        membro.setTipo(TipoMembro.VOLUNTARIO);
    }

    @Test
    void testCreateMembro() {
        service.create(membro);
    }
}