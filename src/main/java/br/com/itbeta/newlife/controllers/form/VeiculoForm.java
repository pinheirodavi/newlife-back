package br.com.itbeta.newlife.controllers.form;

import br.com.itbeta.newlife.conversor.EntityToDto;
import br.com.itbeta.newlife.models.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VeiculoForm {

    private Long idApto;
    private String placa;
    private String marca;
    private String modelo;
    private String cor;

    public VeiculoForm (Veiculo veiculo){
        this.idApto = veiculo.getApartamento().getIdApto();
        this.placa = veiculo.getPlaca();
        this.marca = veiculo.getMarca();
        this.modelo = veiculo.getModelo();
        this.cor = veiculo.getCor();
    }
}
