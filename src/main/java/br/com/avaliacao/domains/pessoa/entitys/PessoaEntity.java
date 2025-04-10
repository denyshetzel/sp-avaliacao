package br.com.avaliacao.domains.pessoa.entitys;

import br.com.avaliacao.domains.endereco.entitys.EnderecoEntity;
import br.com.avaliacao.domains.lotacao.entitys.LotacaoEntity;
import br.com.avaliacao.domains.pessoa.dtos.PessoaRequest;
import br.com.avaliacao.exceptions.NotFoundException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pessoa")
@Getter
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public abstract class PessoaEntity implements Serializable {

    @Id
    @Column(name = "pes_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 1, max = 200)
    @Column(name = "pes_nome", nullable = false, length = 200)
    private String nome;

    @Temporal(TemporalType.DATE)
    @Column(name = "pes_data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "pes_sexo", nullable = false, length = 9)
    private Sexo sexo;

    @Size(min = 1, max = 200)
    @Column(name = "pes_mae", nullable = false, length = 200)
    private String nomeMae;

    @Size(min = 1, max = 200)
    @Column(name = "pes_pai", nullable = false, length = 200)
    private String nomePai;

    @NotNull
    @Column(nullable = false, length = 10)
    public TipoServidor tipoServidor;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PessoaFotoEntity> fotos;

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private LotacaoEntity lotacao;

    @OneToOne(cascade=CascadeType.MERGE)
    @JoinTable(name = "pessoa_endereco",
            joinColumns = @JoinColumn(name = "pes_id", referencedColumnName = "pes_id"),
            inverseJoinColumns = @JoinColumn(name = "end_id", referencedColumnName = "end_id"))
    private EnderecoEntity endereco;

    protected PessoaEntity() {
        this.fotos = new HashSet<>();
    }

    public void update(PessoaRequest pessoaUpdate) {
        this.nome = pessoaUpdate.getNome();
        this.dataNascimento = pessoaUpdate.getDataNascimento();
        this.sexo = pessoaUpdate.getSexo();
        this.nomeMae = pessoaUpdate.getNomeMae();
        this.nomePai = pessoaUpdate.getNomePai();
    }

    public Set<PessoaFotoEntity> getFotos() {
        return ObjectUtils.isEmpty(fotos) ? Collections.emptySet() : Collections.unmodifiableSet(fotos);
    }

    public void addFoto(PessoaFotoEntity pessoaFoto) {
        fotos.add(pessoaFoto);
    }

    public PessoaFotoEntity removeFoto(Integer fotoId) {
        PessoaFotoEntity pessoaFotoEntity = this.fotos
                .stream()
                .filter(foto -> foto.getId().equals(fotoId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(fotoId));
        fotos.remove(pessoaFotoEntity);
        return pessoaFotoEntity;
    }

    public void addEndereco(EnderecoEntity endereco) {
        this.endereco = endereco;
    }

    public void removeEndereco() {
        this.endereco = null;
    }

    public void addLotacao(LotacaoEntity lotacao) {
        lotacao.setPessoa(this);
        this.lotacao = lotacao;
    }

    public void removeLotacao() {
        this.lotacao = null;
    }
}
