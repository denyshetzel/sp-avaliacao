package br.com.avaliacao.avaliacao.endereco;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cidade")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Cidade {

    @Id
    @Column(name = "cid_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cid_nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "cid_uf", nullable = false, length = 2)
    private String uf;

}