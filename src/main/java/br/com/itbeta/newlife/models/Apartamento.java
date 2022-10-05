package br.com.itbeta.newlife.models;

import br.com.itbeta.newlife.controllers.form.ApartamentoForm;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "apartamentos")
public class Apartamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idApto")
    private Long idApto;
    @Column(name = "numApto")
    private Long numApto;

//    @Builder.Default
//    @OneToMany(mappedBy = "apartamento", cascade = CascadeType.ALL,orphanRemoval = true)
//    Set<Morador> moradores = new HashSet<>();
//
//    @Builder.Default
//    @OneToMany(mappedBy = "apartamento", cascade = CascadeType.ALL,orphanRemoval = true)
//    Set<Morador> funcionarios = new HashSet<>();
//
//    @Builder.Default
//    @OneToMany(mappedBy = "apartamento", cascade = CascadeType.ALL,orphanRemoval = true)
//    Set<Morador> veiculos = new HashSet<>();
//
//    @Builder.Default
//    @OneToMany(mappedBy = "apartamento", cascade = CascadeType.ALL,orphanRemoval = true)
//    Set<Morador> visitantes = new HashSet<>();

    public Apartamento (ApartamentoForm form){
        this.numApto = form.getNumApto();
    }

    public void update(ApartamentoForm form) {
        this.numApto = form.getNumApto();
    }
}
