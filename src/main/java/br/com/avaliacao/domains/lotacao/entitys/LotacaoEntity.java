package br.com.avaliacao.domains.lotacao.entitys;

import br.com.avaliacao.domains.lotacao.dtos.LotacaoRequest;
import br.com.avaliacao.domains.pessoa.entitys.PessoaEntity;
import br.com.avaliacao.domains.unidade.entitys.UnidadeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "lotacao")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class LotacaoEntity {

    @Id
    @Column(name = "lot_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @NotNull
    @OneToOne
    @JoinColumn(name = "pes_id")
    private PessoaEntity pessoa;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "uni_id")
    private UnidadeEntity unidade;

    @NotNull
    @Column(name = "lot_data_lotacao", nullable = false)
    private LocalDate dataLotacao;

    @Column(name = "lot_data_remocao")
    private LocalDate dataRemocao;

    @NotNull
    @Column(name = "lot_portaria", nullable = false, length = 100)
    private Integer portaria;

    public void update(LotacaoRequest lotacaUpdate) {
        this.dataRemocao = lotacaUpdate.getDataRemocao();
        this.portaria = lotacaUpdate.getPortaria();
    }

}
