package com.barbearia.Barbearia_Completo.service;

import com.barbearia.Barbearia_Completo.dto.BarbeariaResponse;
import com.barbearia.Barbearia_Completo.dto.BarbeiroRequest;
import com.barbearia.Barbearia_Completo.model.BarbeiroModel;
import com.barbearia.Barbearia_Completo.repository.BarbeiroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BarbeiroService {
    private final BarbeiroRepository repository;

    public BarbeiroService(BarbeiroRepository repository) {
        this.repository = repository;
    }
    public BarbeariaResponse criar(BarbeiroRequest request){
        String name = normalizename(request.getName());

        BarbeiroModel barbeiro = new BarbeiroModel();
        barbeiro.setName(name);
        barbeiro.setActive(true);
        BarbeiroModel saved = repository.save(barbeiro);
        return new BarbeariaResponse(saved.getId(), saved.getName(), saved.getActive());
    }
    public List<BarbeariaResponse> listarBarbeiro(){
        return repository.findByActiveTrue().stream().map(barbeiro -> new BarbeariaResponse(barbeiro.getId(),barbeiro.getName(), barbeiro.getActive())).toList();    }

    public void delete(Long id){
        BarbeiroModel barbearia = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Barbeiro nao encontrado"));

        barbearia.setActive(false);
        repository.save(barbearia);
    }

    public BarbeariaResponse atualizar(Long id, BarbeiroRequest request){
        BarbeiroModel barbearia = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Barbeiro nao encontrado"));
            String name = normalizename(request.getName());
        if(!barbearia.getName().equalsIgnoreCase(name) && repository.existsByNameIgnoreCase(name)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Barbeiro já existente");
        }

       barbearia.setName(name);
        BarbeiroModel saved = repository.save(barbearia);
        return new BarbeariaResponse(saved.getId(), saved.getName(), saved.getActive());
    }


    private String normalizename(String name) {
        return name.trim().toLowerCase();
    }
}
