package br.com.itbeta.newlife.models;

import br.com.itbeta.newlife.controllers.form.VeiculoForm;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "veiculos")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVeiculo")
    private Long idVeiculo;
    @JoinColumn(name = "idApto")
    @ManyToOne
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private Apartamento apartamento;
    @Column(name = "placa")
    private String placa;
    @Column(name = "marca")
    private String marca;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "cor")
    private String cor;

    public void addApartamentos(Apartamento idApto){
        this.apartamento = idApto;
    }

    public void update(VeiculoForm form) {
        this.placa = form.getPlaca();
        this.marca = form.getMarca();
        this.modelo = form.getModelo();
        this.cor = form.getCor();
    }
}
