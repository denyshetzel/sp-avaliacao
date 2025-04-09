package br.com.avaliacao.domains.unidade.entitys;

import br.com.avaliacao.domains.endereco.entitys.EnderecoEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "unidade_endereco")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class UnidadeEnderecoEntity {

    @Id
    @Column(name = "uni_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "uni_id")
    private UnidadeEntity unidade;

    @OneToOne
    @JoinColumn(name = "end_id")
    private EnderecoEntity endereco;
}
