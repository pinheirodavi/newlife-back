package br.com.itbeta.newlife.services;

import br.com.itbeta.newlife.controllers.dtos.MoradorDTO;
import br.com.itbeta.newlife.controllers.dtos.VisitanteDTO;
import br.com.itbeta.newlife.controllers.form.MoradorForm;
import br.com.itbeta.newlife.controllers.form.VisitanteForm;
import br.com.itbeta.newlife.models.Morador;
import br.com.itbeta.newlife.models.Visitante;
import br.com.itbeta.newlife.repositories.ApartamentoRepository;
import br.com.itbeta.newlife.repositories.VisitanteRepository;
import br.com.itbeta.newlife.repositories.specifications.MoradorSpecifications;
import br.com.itbeta.newlife.repositories.specifications.VisitanteSpecifications;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class VisitanteService {

    private final VisitanteRepository repository;

    private final ApartamentoRepository apartamentoRepository;

    public VisitanteForm findByid(Long id) {
        Visitante v = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new VisitanteForm(v);
    }


    public Page<Visitante> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }


    public Page<Visitante> findAll(Pageable pageable, String query) {
        return this.repository.findAll(VisitanteSpecifications.likeGenericQuery(query), pageable);
    }

    public void createVisitante(VisitanteForm form) {
        Visitante v = Visitante
                .builder()
                .nome(form.getNome())
                .rg(form.getRg())
                .cpf(form.getCpf())
                .tel1(form.getTel1())
                .tel2(form.getTel2())
                .obs(form.getObs())
                .build();
        v.addApartamentos(this.apartamentoRepository.findById(form.getIdApto()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        this.repository.save(v);
    }

    public void updateVisitante(Long id, VisitanteForm form) {
        Visitante v = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        v.addApartamentos(this.apartamentoRepository.findById(form.getIdApto()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        v.update(form);

        repository.save(v);
    }

    public void deleteVisitante(Long id) {
        Visitante v = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        repository.delete(v);
    }

}
