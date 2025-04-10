package br.com.avaliacao.domains.unidade.entitys;

import br.com.avaliacao.domains.endereco.entitys.EnderecoEntity;
import br.com.avaliacao.domains.unidade.dtos.UnidadeRequest;
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
public class UnidadeEntity {

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

    @OneToOne(cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "unidade_endereco",
            joinColumns = @JoinColumn(name = "uni_id", referencedColumnName = "uni_id"),
            inverseJoinColumns = @JoinColumn(name = "end_id", referencedColumnName = "end_id"))
    private EnderecoEntity endereco;

    public void update(UnidadeRequest unidadeDTO) {
        this.nome = unidadeDTO.getNome();
        this.sigla = unidadeDTO.getSigla();
    }

    public void addEndereco(EnderecoEntity endereco) {
        this.endereco = endereco;
    }

    public void removeEndereco() {
        this.endereco = null;
    }

}
