package br.com.avaliacao.domains.pessoa.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "foto_pessoa")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class PessoaFotoEntity {

    @Id
    @Column(name = "fp_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "pes_id")
    private PessoaEntity pessoa;

    @NotNull
    @Column(name = "fp_data", nullable = false)
    private LocalDate data;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "fp_bucket", nullable = false, length = 50)
    private String bucket;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "fp_hash", nullable = false, length = 50)
    private String hash;

}
