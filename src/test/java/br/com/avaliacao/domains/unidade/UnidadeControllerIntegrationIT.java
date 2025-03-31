package br.com.avaliacao.domains.unidade;

import br.com.avaliacao.Application;
import br.com.avaliacao.config.AppConstantes;
import br.com.avaliacao.mocks.UnidadeMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UnidadeControllerIntegrationIT {

    @Autowired
    private WebTestClient webClient;

    @LocalServerPort
    private int port;

    @Autowired
    private UnidadeRepository unidadeRepository;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/api" + AppConstantes.PATHS.V1.UNIDADES;
    }

    @Test
    @DisplayName("Deve retornar todas as unidades")
    void deve_retornar_todas_as_unidades() {
        var unidade = UnidadeMock.unidadeDB();
        unidadeRepository.save(unidade);

        webClient.get().uri(baseUrl)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Unidade.class)
                .value(unidades -> {
                    assertNotNull(unidades);
                    assertEquals(1, unidades.size());
                });
    }

    @Test
    @DisplayName("Deve retornar unidade pelo ID")
    void deve_retornar_unidade_pelo_id() {
        var unidade = UnidadeMock.unidadeDB();
        Unidade unidadeSaved = unidadeRepository.save(unidade);

        webClient.get().uri(baseUrl + "/" + unidade.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Unidade.class)
                .value(response -> {
                    assertNotNull(response);
                    assertEquals(unidadeSaved.getNome(), response.getNome());
                });
    }

    @Test
    @DisplayName("Deve retornar 404 quando unidade não encontrada pelo ID")
    void deve_retornar_404_quando_unidade_nao_encontrada_pelo_id() {
        webClient.get().uri(baseUrl + "/999")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @DisplayName("Deve criar uma nova unidade")
    void deve_criar_nova_unidade() {
        var unidade = UnidadeMock.unidade();

        webClient.post().uri(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(unidade)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().exists("Location")
                .expectBody(Void.class);
    }

    @Test
    @DisplayName("Deve atualizar uma unidade existente")
    void deve_atualizar_unidade_existente() {
        var unidade = UnidadeMock.unidadeDB();
        unidade = unidadeRepository.save(unidade);

        var unidadeUpdate = UnidadeMock.unidadeDTO();
        unidadeUpdate.setNome("Unidade Atualizada");

        webClient.put().uri(baseUrl + "/" + unidade.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(unidadeUpdate)
                .exchange()
                .expectStatus().isNoContent();

        Optional<Unidade> updatedUnidade = unidadeRepository.findById(unidade.getId());
        assertEquals("Unidade Atualizada", updatedUnidade.get().getNome());
    }

    @Test
    @DisplayName("Deve deletar unidade pelo ID")
    void deve_deletar_unidade_pelo_id() {
        var unidade = UnidadeMock.unidade();
        unidade = unidadeRepository.save(unidade);

        webClient.delete().uri(baseUrl + "/" + unidade.getId())
                .exchange()
                .expectStatus().isNoContent();

        Optional<Unidade> deletedUnidade = unidadeRepository.findById(unidade.getId());
        assertEquals(Optional.empty(), deletedUnidade);
    }

}