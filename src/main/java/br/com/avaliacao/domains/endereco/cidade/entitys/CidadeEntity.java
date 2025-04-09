package br.com.avaliacao.domains.endereco.cidade.entitys;

import br.com.avaliacao.domains.endereco.cidade.dtos.CidadeRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cidade")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class CidadeEntity {

    @Id
    @Column(name = "cid_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cid_nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "cid_uf", nullable = false, length = 2)
    private String uf;

    public void update(CidadeRequest cidadeUpdate) {
        this.nome = cidadeUpdate.getNome();
        this.uf = cidadeUpdate.getUf();
    }

}