package com.barbearia.Barbearia_Completo.controller;

import com.barbearia.Barbearia_Completo.dto.ServicoRequest;
import com.barbearia.Barbearia_Completo.dto.ServicoResponse;
import com.barbearia.Barbearia_Completo.service.ServicoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoService service;

    public ServicoController(ServicoService service) {
        this.service = service;
    }

    @GetMapping
    public List<ServicoResponse> listar(){
    return service.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoResponse criar(@RequestBody ServicoRequest request){
        return service.criar(request);
    }
@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.deletar(id);
    }

    @PutMapping("/{id}")
    public ServicoResponse atualizar(@PathVariable Long id, @RequestBody ServicoRequest request){
        return service.atualizar(id, request);
    }
}
