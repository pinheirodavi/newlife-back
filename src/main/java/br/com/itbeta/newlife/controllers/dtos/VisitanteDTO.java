package br.com.itbeta.newlife.controllers.dtos;

import br.com.itbeta.newlife.conversor.EntityToDto;
import br.com.itbeta.newlife.models.Morador;
import br.com.itbeta.newlife.models.Visitante;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor@Getter
public class VisitanteDTO {

    private Long idVisitante;
    private ApartamentoDTO idApto;
    private String nome;
    private String rg;
    private String cpf;
    private String tel1;
    private String tel2;
    private String obs;

    public VisitanteDTO (Visitante visitante) {
        EntityToDto entityToDto = new EntityToDto();
        this.idVisitante = visitante.getIdVisitante();
        this.idApto = entityToDto.converter(visitante.getApartamento());
        this.nome = visitante.getNome();
        this.rg = visitante.getRg();
        this.cpf = visitante.getCpf();
        this.tel1 = visitante.getTel1();
        this.tel2 = visitante.getTel2();
        this.obs = visitante.getObs();
    }

}
