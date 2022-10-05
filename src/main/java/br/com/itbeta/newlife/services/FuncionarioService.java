package br.com.itbeta.newlife.services;

import br.com.itbeta.newlife.controllers.dtos.FuncionarioDTO;
import br.com.itbeta.newlife.controllers.form.FuncionarioForm;
import br.com.itbeta.newlife.models.Funcionario;
import br.com.itbeta.newlife.models.Funcionario_;
import br.com.itbeta.newlife.repositories.ApartamentoRepository;
import br.com.itbeta.newlife.repositories.FuncionarioRepository;
import br.com.itbeta.newlife.repositories.specifications.FuncionarioSpecifications;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository repository;

    private final ApartamentoRepository apartamentoRepository;

    public FuncionarioForm findById(Long id) {
        Funcionario f = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new FuncionarioForm(f);
    }

    public Page<Funcionario> findAll(Pageable pageable){
        return this.repository.findAll(pageable);
    }

    public Page<Funcionario> findAll(Pageable pageable, String query) {
        return this.repository.findAll(FuncionarioSpecifications.likeGenericQuery(query), pageable);
    }

    public void createFuncionario(FuncionarioForm form) {
        Funcionario f = Funcionario
                .builder()
                .nome(form.getNome())
                .rg(form.getRg())
                .cpf(form.getCpf())
                .tel1(form.getTel1())
                .tel2(form.getTel2())
                .obs(form.getObs())
                .build();
        f.addApartamentos(this.apartamentoRepository.findById(form.getIdApto()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        this.repository.save(f);
    }

    public void updateFuncionario(Long id, FuncionarioForm form) {
        Funcionario f = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        f.addApartamentos(this.apartamentoRepository.findById(form.getIdApto()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        f.update(form);
        repository.save(f);
    }

    public void deleteFuncionario(Long id) {
        Funcionario f = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        repository.delete(f);
    }

}
