package br.com.itbeta.newlife.models;

import br.com.itbeta.newlife.controllers.form.FuncionarioForm;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "funcionarios")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFuncionario")
    private Long idFuncionario;
    //@OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "idApto")
    @ManyToOne
    private Apartamento apartamento;
    @Column(name = "funcionario")
    private String nome;
    @Column(name = "rg")
    private String rg;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "tel1")
    private String tel1;
    @Column(name = "tel2")
    private String tel2;
    @Column(name = "obs")
    private String obs;

    public void addApartamentos(Apartamento idApto){
        this.apartamento = idApto;
    }

    public void update(FuncionarioForm form) {
        this.nome = form.getNome();
        this.rg = form.getRg();
        this.cpf = form.getCpf();
        this.tel1 = form.getTel1();
        this.tel2 = form.getTel2();
        this.obs = form.getObs();
    }
}
