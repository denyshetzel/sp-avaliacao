package br.com.avaliacao.domains.unidade;

import br.com.avaliacao.exceptions.NotFoundException;
import br.com.avaliacao.mocks.PageableMock;
import br.com.avaliacao.mocks.UnidadeMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UnidadeServiceTest {

    @Mock
    private UnidadeRepository unidadeRepository;

    @Mock
    private UnidadeMapper unidadeMapper;

    @InjectMocks
    private UnidadeService unidadeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve retornar uma lista paginada")
    void deve_retornar_lista_paginada() {
        var pageable = PageableMock.pageable();
        var unidade = UnidadeMock.unidade();
        var unidadeDTO = UnidadeMock.unidadeDTO();
        var unidadePage = PageableMock.createPage(Collections.singletonList(unidade));

        when(unidadeRepository.findAll(pageable)).thenReturn(unidadePage);
        when(unidadeMapper.toDTO(any(Unidade.class))).thenReturn(unidadeDTO);

        var result = unidadeService.findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(unidadeRepository, times(1)).findAll(pageable);
    }

    @Test
    @DisplayName("Deve retornar pelo ID")
    void deve_retornar_pelo_id() {
        var id = 1;
        var unidade = UnidadeMock.unidade();
        var unidadeDTO = UnidadeMock.unidadeDTO();

        when(unidadeRepository.findById(id)).thenReturn(Optional.of(unidade));
        when(unidadeMapper.toDTO(unidade)).thenReturn(unidadeDTO);

        var result = unidadeService.findById(id);

        assertNotNull(result);
        verify(unidadeRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve lançar NotFoundException quando Unidade não encontrada")
    void deve_lancar_notfoundexception_recurso_nao_encontrado() {
        var id = 1;

        when(unidadeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> unidadeService.findById(id));
        verify(unidadeRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve salvar uma Unidade")
    void deve_salvar_unidade() {
        var unidadeDTO = UnidadeMock.unidadeDTO();
        var unidade = new Unidade();

        when(unidadeMapper.toEntity(unidadeDTO)).thenReturn(unidade);
        when(unidadeRepository.save(unidade)).thenReturn(unidade);
        when(unidadeMapper.toDTO(unidade)).thenReturn(unidadeDTO);

        var result = unidadeService.save(unidadeDTO);

        assertNotNull(result);
        verify(unidadeRepository, times(1)).save(unidade);
    }

    @Test
    @DisplayName("Deve deletar pelo ID")
    void deve_deletar__pelo_id() {
        var id = 1;

        when(unidadeRepository.existsById(id)).thenReturn(true);

        unidadeService.deleteById(id);

        verify(unidadeRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Deve lançar NotFoundException ao tentar deletar recurso não encontrada")
    void deve_lancar_notfoundexcption_ao_tentar_deletar_unidade_nao_encontrada() {
        var id = 1;

        when(unidadeRepository.existsById(id)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> unidadeService.deleteById(id));
        verify(unidadeRepository, never()).deleteById(id);
    }

    @Test
    @DisplayName("Deve atualizar um recurso")
    void deve_atualizar() {
        var id = 1;
        var unidadeDTO = UnidadeMock.unidadeDTO();
        var unidade = UnidadeMock.unidade();

        when(unidadeRepository.findById(id)).thenReturn(Optional.of(unidade));

        unidadeService.updateUnidade(id, unidadeDTO);

        verify(unidadeRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve lançar NotFoundException ao tentar atualizar recurso não encontrado")
    void deve_lancar_notfoundexcetption_ao_tentar_atualizar_nao_encontrada() {
        var id = 1;
        var unidadeDTO = UnidadeMock.unidadeDTO();

        when(unidadeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> unidadeService.updateUnidade(id, unidadeDTO));
        verify(unidadeRepository, times(1)).findById(id);
    }
}