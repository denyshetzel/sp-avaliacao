package br.com.avaliacao.avaliacao.pessoa;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "foto_pessoa")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class FotoPessoa {

    @Id
    @Column(name = "fp_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pes_id", nullable = false)
    private Integer pessoaId;

    @Column(name = "fp_data", nullable = false)
    private LocalDate data;

    @Column(name = "fp_bucket", nullable = false, length = 50)
    private String bucket;

    @Column(name = "fp_hash", nullable = false, length = 50)
    private String hash;

}
