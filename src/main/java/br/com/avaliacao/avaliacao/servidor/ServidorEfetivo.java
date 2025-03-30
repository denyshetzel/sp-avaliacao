package br.com.avaliacao.avaliacao.servidor;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "servidor_efetivo")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ServidorEfetivo {

    @Id
    @Column(name = "pes_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "se_matricular", length = 20)
    private String matricula;

}
