package br.com.itbeta.newlife.controllers.dtos;

import br.com.itbeta.newlife.models.Apartamento;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApartamentoDTO {
    private Long idApto;
    private Long numApto;

    public ApartamentoDTO(Apartamento apartamento) {
        this.idApto = apartamento.getIdApto();
        this.numApto = apartamento.getNumApto();
    }

}
