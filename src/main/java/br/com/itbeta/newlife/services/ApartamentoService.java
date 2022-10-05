package br.com.itbeta.newlife.services;

import br.com.itbeta.newlife.controllers.dtos.ApartamentoDTO;
import br.com.itbeta.newlife.controllers.form.ApartamentoForm;
import br.com.itbeta.newlife.models.Apartamento;
import br.com.itbeta.newlife.repositories.ApartamentoRepository;
import br.com.itbeta.newlife.repositories.projections.AptoDetails;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class ApartamentoService {

    private final ApartamentoRepository repository;

    public ApartamentoDTO findById(Long id) {
        Apartamento a = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ApartamentoDTO(a);
    }

    public Page<Apartamento> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public Page<Apartamento> findAll(Pageable pageable, String query) {
        return this.repository.findAllQuery(pageable, query);
    }

    public void createApartamento(ApartamentoForm form) {
        Apartamento a = new Apartamento(form);
        this.repository.save(a);
    }

    public void updateApartamento(Long id, ApartamentoForm form) {
        Apartamento a = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        a.update(form);
        repository.save(a);
    }

    public void deleteApartamento(Long id) {
        Apartamento a = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        repository.delete(a);
    }

    public List<AptoDetails> findAllAptos() {
       return this.repository.findAllAptos();
    }

//    public Page<ApartamentoDTO> findAllApto(Pageable pageable) {
//        return this.repository.findAllApto(pageable);
//    }
}
