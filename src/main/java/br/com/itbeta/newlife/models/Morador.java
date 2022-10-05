package br.com.itbeta.newlife.models;

import br.com.itbeta.newlife.controllers.form.MoradorForm;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "moradores")
public class Morador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMorador")
    private Long idMorador;
    @JoinColumn(name = "idApto")
    @ManyToOne
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private Apartamento apartamento;
    @Column(name = "morador")
    private String nome;
    @Column(name = "rg")
    private String rg;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "tel1")
    private String tel1;
    @Column(name = "tel2")
    private String tel2;
    @Column(name = "email")
    private String email;
    @Column(name = "contatoEmergencia")
    private String contatoEmergencia;
    @Column(name = "telEmergencia")
    private String telEmergencia;
    @Column(name = "obs")
    private String obs;

    public Morador(MoradorForm form) {
        this.nome = form.getNome();
        this.rg = form.getRg();
        this.cpf = form.getCpf();
        this.tel1 = form.getTel1();
        this.tel2 = form.getTel2();
        this.email = form.getEmail();
        this.contatoEmergencia = form.getContatoEmergencia();
        this.telEmergencia = form.getTelEmergencia();
        this.obs = form.getObs();
    }

    public void update(MoradorForm form) {
        this.nome = form.getNome();
        //this.apartamento = form.getIdApto();
        this.rg = form.getRg();
        this.cpf = form.getCpf();
        this.tel1 = form.getTel1();
        this.tel2 = form.getTel2();
        this.email = form.getEmail();
        this.contatoEmergencia = form.getContatoEmergencia();
        this.telEmergencia = form.getTelEmergencia();
        this.obs = form.getObs();
    }

    public void addApartamentos(Apartamento idApto){
        this.apartamento = idApto;
    }
}
