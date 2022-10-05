package br.com.itbeta.newlife.controllers.form;

import br.com.itbeta.newlife.conversor.EntityToDto;
import br.com.itbeta.newlife.models.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FuncionarioForm {

    private Long idApto;
    private String nome;
    private String rg;
    private String cpf;
    private String tel1;
    private String tel2;
    private String obs;

    public FuncionarioForm(Funcionario funcionario) {
        this.idApto = funcionario.getApartamento().getIdApto();
        this.nome = funcionario.getNome();
        this.rg = funcionario.getRg();
        this.cpf = funcionario.getCpf();
        this.tel1 = funcionario.getTel1();
        this.tel2 = funcionario.getTel2();
        this.obs = funcionario.getObs();

    }
}
