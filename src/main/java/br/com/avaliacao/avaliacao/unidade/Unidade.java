package br.com.avaliacao.avaliacao.unidade;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "unidade")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Unidade {

    @Id
    @Column(name = "uni_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uni_nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "uni_nome", nullable = false, length = 20)
    private String sigla;

}
