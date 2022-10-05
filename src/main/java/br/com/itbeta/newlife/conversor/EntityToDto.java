package br.com.itbeta.newlife.conversor;

import br.com.itbeta.newlife.controllers.dtos.ApartamentoDTO;
import br.com.itbeta.newlife.models.Apartamento;

public class EntityToDto {

    public ApartamentoDTO converter(Apartamento a) {

        return new ApartamentoDTO(a);

    }

}
