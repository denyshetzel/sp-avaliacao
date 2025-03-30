package br.com.avaliacao.avaliacao.unidade;

import br.com.avaliacao.avaliacao.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnidadeService {

    private final UnidadeRepository unidadeRepository;
    private final UnidadeMapper unidadeMapper;


    public List<Unidade> findAll() {
        return unidadeRepository.findAll();
    }

    public UnidadeDTO findById(Integer id) {
        Optional<Unidade> unidade = unidadeRepository.findById(id);

        return unidade.map(unidadeMapper::toDTO)
                .orElseThrow(() -> new NotFoundException(String.format("Unidade %s não encontrada", id)));
    }

    public UnidadeDTO save(Unidade unidade) {
        unidadeRepository.save(unidade);
        return unidadeMapper.toDTO(unidade);
    }

    public void deleteById(Integer id) {
        var exists = unidadeRepository.existsById(id);
        if(!exists) throw new NotFoundException(String.format("Unidade %s não encontrada", id));
        unidadeRepository.deleteById(id);
    }

    public Object updateUnidade(Integer id, UnidadeDTO unidadeUpdate) {
        unidadeMapper.toEntity(id, unidadeUpdate);
    }

}