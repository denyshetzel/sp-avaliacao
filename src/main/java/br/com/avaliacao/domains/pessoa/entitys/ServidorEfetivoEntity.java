package br.com.avaliacao.domains.pessoa.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "servidor_efetivo")
@PrimaryKeyJoinColumn(name = "pes_id")
@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServidorEfetivoEntity extends PessoaEntity {

    @NotBlank
    @Size(min = 1, max = 20)
    @Column(name = "sef_matricula", nullable = false, length = 20)
    private String matricula;
}
