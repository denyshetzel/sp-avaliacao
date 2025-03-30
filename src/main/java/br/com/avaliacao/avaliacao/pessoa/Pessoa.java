package br.com.avaliacao.avaliacao.pessoa;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "pessoa")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Pessoa {

    @Id
    @Column(name = "pes_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pes_nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "pes_data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "pes_sexo", nullable = false, length = 9)
    private Sexo sexo;

    @Column(name = "pes_mae", nullable = false, length = 200)
    private String nomeMae;

    @Column(name = "pes_pai", nullable = false, length = 200)
    private String nomePai;

}
