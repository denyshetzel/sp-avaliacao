package br.com.avaliacao.avaliacao.pessoa;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pessoa_endereco")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class PessoaEndereco {

    @Id
    @Column(name = "end_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pes_id", nullable = false)
    private Integer pessoaId;

}
