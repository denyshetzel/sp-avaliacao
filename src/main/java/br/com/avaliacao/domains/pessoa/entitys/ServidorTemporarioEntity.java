package br.com.avaliacao.domains.pessoa.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "servidor_temporario")
@PrimaryKeyJoinColumn(name = "pes_id")
@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServidorTemporarioEntity extends PessoaEntity {

    @NotNull
    @Column(name = "st_data_admissao")
    private LocalDate dataAdmissao;

    @NotNull
    @Column(name = "st_data_demissao")
    private LocalDate dataDemissao;

}