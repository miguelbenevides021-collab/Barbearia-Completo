package com.barbearia.Barbearia_Completo.controller;

import com.barbearia.Barbearia_Completo.dto.BarbeariaResponse;
import com.barbearia.Barbearia_Completo.dto.BarbeiroRequest;
import com.barbearia.Barbearia_Completo.service.BarbeiroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barbeiro")
public class BarbeiroController {
    private BarbeiroService service;

    public BarbeiroController(BarbeiroService barbeiroService){
        this.service = barbeiroService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BarbeariaResponse criarbarbeiro(@Valid @RequestBody BarbeiroRequest request){
        return service.criar(request);
    }

    @GetMapping
    public List<BarbeariaResponse> listar( ){
        return service.listarBarbeiro();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
      service.delete(id);
    }

    @PutMapping("/{id}")
    public BarbeariaResponse put(@PathVariable Long id, @Valid @RequestBody BarbeiroRequest request){
        return service.atualizar(id, request);
    }
}
