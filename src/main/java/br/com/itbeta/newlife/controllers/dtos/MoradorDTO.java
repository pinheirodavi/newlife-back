package br.com.itbeta.newlife.controllers.dtos;

import br.com.itbeta.newlife.conversor.EntityToDto;
import br.com.itbeta.newlife.models.Morador;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MoradorDTO {

    private Long idMorador;
    private ApartamentoDTO idApto;
    private String nome;
    private String rg;
    private String cpf;
    private String tel1;
    private String tel2;
    private String email;
    private String contatoEmergencia;
    private String telEmergencia;
    private String obs;

    public MoradorDTO (Morador morador) {
        EntityToDto entityToDto = new EntityToDto();
        this.idMorador = morador.getIdMorador();
        this.idApto = entityToDto.converter(morador.getApartamento());
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
