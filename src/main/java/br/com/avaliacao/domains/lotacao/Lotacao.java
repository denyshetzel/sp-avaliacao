package br.com.avaliacao.domains.lotacao;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "lotacao")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Lotacao {

    @Id
    @Column(name = "lot_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pes_id", nullable = false)
    private Integer pessoaId;

    @Column(name = "uni_id", nullable = false)
    private Integer unidadeId;

    @Column(name = "lot_data_lotacao", nullable = false)
    private LocalDate dataLotacao;

    @Column(name = "lot_data_remocao", nullable = false)
    private LocalDate dataRemocao;

    @Column(name = "lot_portaria", nullable = false, length = 100)
    private Integer portaria;

}
