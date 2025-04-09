package br.com.avaliacao.domains.pessoa.entitys;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pessoa_endereco")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class PessoaEnderecoEntity {

    @Id
    @Column(name = "end_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private PessoaEntity pessoa;

}
