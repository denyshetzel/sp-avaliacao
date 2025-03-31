package br.com.avaliacao.domains.unidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "unidade", indexes = @Index(columnList = "uni_sigla"))
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Unidade {

    @Id
    @Column(name = "uni_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 1, max = 200)
    @Column(name = "uni_nome", nullable = false, length = 200)
    private String nome;

    @NotBlank
    @Size(min = 1, max = 20)
    @Column(name = "uni_sigla", nullable = false, length = 20)
    private String sigla;

    public void update(UnidadeDTO unidadeDTO) {
        this.nome = unidadeDTO.getNome();
        this.sigla = unidadeDTO.getSigla();
    }

}
