package br.com.itbeta.newlife.controllers.dtos;

import br.com.itbeta.newlife.conversor.EntityToDto;
import br.com.itbeta.newlife.models.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FuncionarioDTO {

    private Long idFuncionario;
    private ApartamentoDTO idApto;
    private String nome;
    private String rg;
    private String cpf;
    private String tel1;
    private String tel2;
    private String obs;

    public FuncionarioDTO (Funcionario funcionario) {
        EntityToDto entityToDto = new EntityToDto();
        this.idFuncionario = funcionario.getIdFuncionario();
        this.idApto = entityToDto.converter(funcionario.getApartamento());
        this.nome = funcionario.getNome();
        this.rg = funcionario.getRg();
        this.cpf = funcionario.getCpf();
        this.tel1 = funcionario.getTel1();
        this.tel2 = funcionario.getTel2();
        this.obs = funcionario.getObs();

    }
}
