package br.com.itbeta.newlife.controllers;

import br.com.itbeta.newlife.controllers.dtos.MoradorDTO;
import br.com.itbeta.newlife.controllers.form.MoradorForm;
import br.com.itbeta.newlife.repositories.projections.MoradorDetails;
import br.com.itbeta.newlife.services.MoradorService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/moradores")
@AllArgsConstructor
public class MoradorController {

    private final MoradorService service;

    private final ConversionService conversionService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        MoradorForm form = this.service.findByid(id);
        return ResponseEntity.status(HttpStatus.OK).body(form);
    }

    @GetMapping("/all")
    public @ResponseBody Page<MoradorDTO> findAllPaginated(@RequestParam(required = false) String query, Pageable pageable) {
        if (query == null) {
            return this.service.findAll(pageable).map(entity -> this.conversionService.convert(entity, MoradorDTO.class));
        }
        return this.service.findAll(pageable, query).map(entity -> this.conversionService.convert(entity, MoradorDTO.class));
    }

    @GetMapping("/allList")
    public @ResponseBody List<MoradorDetails> findAllList(@RequestParam(required = false) String query){
        System.out.println(query);
        if(query == null) {
            return this.service.findAllList();
        }
        return this.service.findAllList(query);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> createMorador(@RequestBody MoradorForm form) {
        this.service.createMorador(form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateMorador(@PathVariable Long id, @RequestBody MoradorForm form) {
        this.service.updateMorador(id, form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMorador(@PathVariable Long id) {
        this.service.deleteMorador(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
