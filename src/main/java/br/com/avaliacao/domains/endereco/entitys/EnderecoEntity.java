package br.com.avaliacao.domains.endereco.entitys;

import br.com.avaliacao.domains.endereco.EnderecoRequest;
import br.com.avaliacao.domains.endereco.cidade.entitys.CidadeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "endereco")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class EnderecoEntity {

    @Id
    @Column(name = "end_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "end_tipo_logradouro", nullable = false, length = 50)
    private String tipoLogradouro;

    @Column(name = "end_logradouro", nullable = false, length = 200)
    private String logradouro;

    @Column(name = "end_numero", nullable = false)
    private Integer numero;

    @Column(name = "end_bairro", nullable = false, length = 100)
    private String bairro;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cid_id")
    private CidadeEntity cidade;

    public void update(EnderecoRequest enderecoRequest) {
        this.tipoLogradouro = enderecoRequest.getTipoLogradouro();
        this.logradouro = enderecoRequest.getLogradouro();
        this.numero = enderecoRequest.getNumero();
        this.bairro = enderecoRequest.getBairro();
        this.cidade = CidadeEntity.builder().id(enderecoRequest.getCidade().getId()).build();
    }

}
