package br.com.itbeta.newlife.controllers.form;

import br.com.itbeta.newlife.conversor.EntityToDto;
import br.com.itbeta.newlife.models.Visitante;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VisitanteForm {

    private Long idApto;
    private String nome;
    private String rg;
    private String cpf;
    private String tel1;
    private String tel2;
    private String obs;

    public VisitanteForm (Visitante visitante) {
        this.idApto = visitante.getApartamento().getIdApto();
        this.nome = visitante.getNome();
        this.rg = visitante.getRg();
        this.cpf = visitante.getCpf();
        this.tel1 = visitante.getTel1();
        this.tel2 = visitante.getTel2();
        this.obs = visitante.getObs();
    }

}
