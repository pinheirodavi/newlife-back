package br.com.itbeta.newlife.controllers;

import br.com.itbeta.newlife.controllers.dtos.MoradorDTO;
import br.com.itbeta.newlife.controllers.dtos.VeiculoDTO;
import br.com.itbeta.newlife.controllers.form.VeiculoForm;
import br.com.itbeta.newlife.services.VeiculoService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/veiculos")
@AllArgsConstructor
public class VeiculoController {
    private VeiculoService service;

    private final ConversionService conversionService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        VeiculoForm form = this.service.findByid(id);
        return ResponseEntity.status(HttpStatus.OK).body(form);
    }

    @GetMapping("/all")
    public @ResponseBody Page<VeiculoDTO> findAllPaginated(@RequestParam(required = false) String query, Pageable pageable) {
        if(query == null){
            return this.service.findAll(pageable).map(entity -> this.conversionService.convert(entity, VeiculoDTO.class));
        }
        return this.service.findAll(pageable, query).map(entity -> this.conversionService.convert(entity, VeiculoDTO.class));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> createVeiculo(@RequestBody VeiculoForm form){
        this.service.createVeiculo(form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateVeiculo(@PathVariable Long id, @RequestBody VeiculoForm form) {
        this.service.updateVeiculo(id, form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVeiculo(@PathVariable Long id) {
        this.service.deleteVeiculo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
