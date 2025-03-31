package br.com.avaliacao.domains.unidade;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "unidade_endereco")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class UnidadeEndereco {

    @Id
    @Column(name = "uni_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "end_id", nullable = false)
    private Integer enderecoId;
}
