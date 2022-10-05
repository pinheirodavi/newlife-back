package br.com.itbeta.newlife.controllers;

import br.com.itbeta.newlife.controllers.dtos.VisitanteDTO;
import br.com.itbeta.newlife.controllers.form.MoradorForm;
import br.com.itbeta.newlife.controllers.form.VisitanteForm;
import br.com.itbeta.newlife.services.VisitanteService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/visitantes")
@AllArgsConstructor
public class VisitanteController {

    private final VisitanteService service;

    private final ConversionService conversionService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        VisitanteForm form = this.service.findByid(id);
        return ResponseEntity.status(HttpStatus.OK).body(form);
    }

    @GetMapping("/all")
    public @ResponseBody Page<VisitanteDTO> findAllPaginated(@RequestParam(required = false) String query, Pageable pageable) {
        if (query == null) {
            return this.service.findAll(pageable).map(entity -> this.conversionService.convert(entity, VisitanteDTO.class));
        }
        return this.service.findAll(pageable, query).map(entity -> this.conversionService.convert(entity, VisitanteDTO.class));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> createVisitante(@RequestBody VisitanteForm form) {
        this.service.createVisitante(form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateVisitante(@PathVariable Long id, @RequestBody VisitanteForm form) {
        this.service.updateVisitante(id, form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVisitante(@PathVariable Long id) {
        this.service.deleteVisitante(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
