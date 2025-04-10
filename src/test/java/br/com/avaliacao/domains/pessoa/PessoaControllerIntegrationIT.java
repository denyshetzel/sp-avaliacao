package br.com.avaliacao.domains.pessoa;

import br.com.avaliacao.Application;
import br.com.avaliacao.config.AppConstantes;
import br.com.avaliacao.domains.pessoa.dtos.PessoaResponse;
import br.com.avaliacao.domains.pessoa.dtos.ServidorEfetivoResponse;
import br.com.avaliacao.domains.pessoa.entitys.PessoaEntity;
import br.com.avaliacao.mocks.PessoaMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PessoaControllerIntegrationIT {

    @Autowired
    private WebTestClient webClient;

    @LocalServerPort
    private int port;

    @Autowired
    private PessoaRepository pessoaRepository;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/api" + AppConstantes.PATHS.V1.PESSOAS;
    }

    @Test
    @DisplayName("Deve retornar todas as pessoas")
    void deve_retornar_todas_as_pessoas() {
        var pessoa = PessoaMock.pessoaDB();
        pessoaRepository.save(pessoa);

        webClient.get().uri(baseUrl)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ServidorEfetivoResponse.class)
                .value(pessoas -> {
                    assertNotNull(pessoas);
                    assertEquals(1, pessoas.size());
                });
    }

    @Test
    @DisplayName("Deve retornar pessoa pelo ID")
    void deve_retornar_pessoa_pelo_id() {
        var pessoa = PessoaMock.pessoaDB();
        PessoaEntity pessoaSaved = pessoaRepository.save(pessoa);

        webClient.get().uri(baseUrl + "/" + pessoaSaved.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ServidorEfetivoResponse.class)
                .value(response -> {
                    assertNotNull(response);
                    assertEquals(pessoaSaved.getNome(), response.getNome());
                });
    }

    @Test
    @DisplayName("Deve criar uma nova pessoa")
    void deve_criar_nova_pessoa() {
        var pessoa = PessoaMock.pessoa();

        webClient.post().uri(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(pessoa)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().exists("Location")
                .expectBody(Void.class);
    }

    @Test
    @DisplayName("Deve atualizar uma pessoa existente")
    void deve_atualizar_pessoa_existente() {
        var pessoa = PessoaMock.pessoaDB();
        pessoa = pessoaRepository.save(pessoa);

        var pessoaUpdate = PessoaMock.pessoaResponse();
        ReflectionTestUtils.setField(pessoaUpdate, "nome", "Pessoa Atualizada");

        webClient.put().uri(baseUrl + "/" + pessoa.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(pessoaUpdate)
                .exchange()
                .expectStatus().isNoContent();

        Optional<PessoaEntity> updatedPessoa = pessoaRepository.findById(pessoa.getId());
        assertEquals("Pessoa Atualizada", updatedPessoa.get().getNome());
    }

    @Test
    @DisplayName("Deve deletar pessoa pelo ID")
    void deve_deletar_pessoa_pelo_id() {
        var pessoa = PessoaMock.pessoa();
        pessoa = pessoaRepository.save(pessoa);

        webClient.delete().uri(baseUrl + "/" + pessoa.getId())
                .exchange()
                .expectStatus().isNoContent();

        Optional<PessoaEntity> deletedPessoa = pessoaRepository.findById(pessoa.getId());
        assertEquals(Optional.empty(), deletedPessoa);
    }
}