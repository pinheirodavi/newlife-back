package br.com.itbeta.newlife.controllers;

import br.com.itbeta.newlife.controllers.dtos.ApartamentoDTO;
import br.com.itbeta.newlife.controllers.form.ApartamentoForm;
import br.com.itbeta.newlife.repositories.projections.AptoDetails;
import br.com.itbeta.newlife.services.ApartamentoService;
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
@RequestMapping("/api/apartamentos")
@AllArgsConstructor
public class ApartamentoController {

    private final ApartamentoService service;

    private final ConversionService conversionService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ApartamentoDTO dto = this.service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
//    @GetMapping("/all")
//    public @ResponseBody Page<ApartamentoDTO> findAllPaginated(@RequestParam(required = false) String query, Pageable pageable) {
//        //if(query == null){
//            return this.service.findAll(pageable).map(entity -> this.conversionService.convert(entity, ApartamentoDTO.class));
//        //}
//        //return this.service.findAll(pageable, query).map(entity -> this.conversionService.convert(entity, ApartamentoDTO.class));
//    }

    @GetMapping("/all")
    public @ResponseBody List<AptoDetails> findAllAptos(){
        return this.service.findAllAptos();
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<?> createApartamento (@RequestBody ApartamentoForm form) {
        this.service.createApartamento(form);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?>updateApartamento (@PathVariable Long id, @RequestBody ApartamentoForm form) {
        this.service.updateApartamento(id, form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteApartamento (@PathVariable Long id) {
        this.service.deleteApartamento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
