package br.com.itbeta.newlife.controllers.dtos;

import br.com.itbeta.newlife.conversor.EntityToDto;
import br.com.itbeta.newlife.models.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VeiculoDTO {

    private Long idVeiculo;
    private ApartamentoDTO idApto;
    private String placa;
    private String marca;
    private String modelo;
    private String cor;

    public VeiculoDTO (Veiculo veiculo){
        EntityToDto entityToDto = new EntityToDto();
        this.idVeiculo = veiculo.getIdVeiculo();
        this.idApto = entityToDto.converter(veiculo.getApartamento());
        this.placa = veiculo.getPlaca();
        this.marca = veiculo.getMarca();
        this.modelo = veiculo.getModelo();
        this.cor = veiculo.getCor();
    }
}
