package br.com.itbeta.newlife.models;

import br.com.itbeta.newlife.controllers.form.FuncionarioForm;
import br.com.itbeta.newlife.controllers.form.MoradorForm;
import br.com.itbeta.newlife.controllers.form.VisitanteForm;
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
@Table(name = "visitantes")
public class Visitante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVisitante")
    private Long idVisitante;
    @JoinColumn(name = "idApto")
    @ManyToOne
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private Apartamento apartamento;
    @Column(name = "visitante")
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

    public void update(VisitanteForm form) {
        this.nome = form.getNome();
        this.rg = form.getRg();
        this.cpf = form.getCpf();
        this.tel1 = form.getTel1();
        this.tel2 = form.getTel2();
        this.obs = form.getObs();
    }
}
