package br.com.itbeta.newlife.services;

import br.com.itbeta.newlife.controllers.dtos.MoradorDTO;
import br.com.itbeta.newlife.controllers.form.MoradorForm;
import br.com.itbeta.newlife.models.Morador;
import br.com.itbeta.newlife.repositories.ApartamentoRepository;
import br.com.itbeta.newlife.repositories.MoradorRepository;
import br.com.itbeta.newlife.repositories.projections.MoradorDetails;
import br.com.itbeta.newlife.repositories.specifications.MoradorSpecifications;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MoradorService {

    private final MoradorRepository repository;

    private final ApartamentoRepository apartamentoRepository;

    public MoradorForm findByid(Long id) {
        Morador m = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new MoradorForm(m);
    }


    public Page<Morador> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }


    public Page<Morador> findAll(Pageable pageable, String query) {
        return this.repository.findAll(MoradorSpecifications.likeGenericQuery(query), pageable);
    }

    public void createMorador(MoradorForm form) {
        Morador m = Morador
                .builder()
                .nome(form.getNome())
                .rg(form.getRg())
                .cpf(form.getCpf())
                .email(form.getEmail())
                .tel1(form.getTel1())
                .tel2(form.getTel2())
                .contatoEmergencia(form.getContatoEmergencia())
                .telEmergencia(form.getTelEmergencia())
                .obs(form.getObs())
                .build();
        m.addApartamentos(this.apartamentoRepository.findById(form.getIdApto()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        this.repository.save(m);
    }

    public void updateMorador(Long id, MoradorForm form) {
        Morador m = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        m.addApartamentos(this.apartamentoRepository.findById(form.getIdApto()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        m.update(form);

        repository.save(m);
    }

    public void deleteMorador(Long id) {
        Morador m = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        repository.delete(m);
    }

    public List<MoradorDetails> findAllList() {
        return this.repository.findAllList();
    }

    public List<MoradorDetails> findAllList(String query) {
        return this.repository.findAllList(query);
    }
}
