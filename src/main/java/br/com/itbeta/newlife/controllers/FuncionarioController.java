package br.com.itbeta.newlife.controllers;

import br.com.itbeta.newlife.controllers.dtos.FuncionarioDTO;
import br.com.itbeta.newlife.controllers.dtos.MoradorDTO;
import br.com.itbeta.newlife.controllers.form.FuncionarioForm;
import br.com.itbeta.newlife.controllers.form.MoradorForm;
import br.com.itbeta.newlife.services.FuncionarioService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/funcionarios")
@AllArgsConstructor
public class FuncionarioController {

    private FuncionarioService service;

    private final ConversionService conversionService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        FuncionarioForm form = this.service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(form);
    }

    @GetMapping("/all")
    public @ResponseBody Page<FuncionarioDTO> findAllPaginated(@RequestParam(required = false) String query, Pageable pageable) {
        if (query == null) {
            return this.service.findAll(pageable).map(entity -> this.conversionService.convert(entity, FuncionarioDTO.class));
        }
        return this.service.findAll(pageable, query).map(entity -> this.conversionService.convert(entity, FuncionarioDTO.class));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> createFuncionario(@RequestBody FuncionarioForm form) {
        this.service.createFuncionario(form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateFuncionario(@PathVariable Long id, @RequestBody FuncionarioForm form) {
        this.service.updateFuncionario(id, form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFuncionario(@PathVariable Long id) {
        this.service.deleteFuncionario(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
