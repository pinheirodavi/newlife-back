package br.com.itbeta.newlife.services;

import br.com.itbeta.newlife.controllers.dtos.VeiculoDTO;
import br.com.itbeta.newlife.controllers.form.VeiculoForm;
import br.com.itbeta.newlife.models.Veiculo;
import br.com.itbeta.newlife.repositories.ApartamentoRepository;
import br.com.itbeta.newlife.repositories.VeiculoRepository;
import br.com.itbeta.newlife.repositories.specifications.VeiculoSpecifications;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VeiculoService {

    private final VeiculoRepository repository;

    private final ApartamentoRepository apartamentoRepository;

    public VeiculoForm findByid(Long id) {
        Veiculo v = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new VeiculoForm(v);
    }

    public Page<Veiculo> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public Page<Veiculo> findAll(Pageable pageable, String query) {
        return this.repository.findAll(VeiculoSpecifications.likeGenericQuery(query), pageable);
    }

    public void createVeiculo(VeiculoForm form) {
        Veiculo v = Veiculo
                .builder()
                .placa(form.getPlaca())
                .marca(form.getMarca())
                .modelo(form.getModelo())
                .cor(form.getCor())
                .build();
        v.addApartamentos(this.apartamentoRepository.findById(form.getIdApto()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        this.repository.save(v);
    }

    public void updateVeiculo(Long id, VeiculoForm form) {
        Veiculo v = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        v.addApartamentos(this.apartamentoRepository.findById(form.getIdApto()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        v.update(form);
        repository.save(v);
    }

    public void deleteVeiculo(Long id) {
        Veiculo v = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        repository.delete(v);
    }
}
