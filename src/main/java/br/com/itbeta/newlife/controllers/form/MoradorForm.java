package br.com.itbeta.newlife.controllers.form;

import br.com.itbeta.newlife.conversor.EntityToDto;
import br.com.itbeta.newlife.models.Morador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MoradorForm {

    private Long idApto;
    private String nome;
    private String rg;
    private String cpf;
    private String email;
    private String tel1;
    private String tel2;
    private String contatoEmergencia;
    private String telEmergencia;
    private String obs;

    public MoradorForm (Morador morador) {
        this.idApto = morador.getApartamento().getIdApto();
        this.nome = morador.getNome();
        this.rg = morador.getRg();
        this.cpf = morador.getCpf();
        this.tel1 = morador.getTel1();
        this.tel2 = morador.getTel2();
        this.email = morador.getEmail();
        this.contatoEmergencia = morador.getContatoEmergencia();
        this.telEmergencia = morador.getTelEmergencia();
        this.obs = morador.getObs();
    }

}
