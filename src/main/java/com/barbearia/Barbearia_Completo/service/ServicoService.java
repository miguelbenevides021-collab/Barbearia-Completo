package com.barbearia.Barbearia_Completo.service;


import com.barbearia.Barbearia_Completo.dto.ServicoRequest;
import com.barbearia.Barbearia_Completo.dto.ServicoResponse;
import com.barbearia.Barbearia_Completo.model.ServicosModel;
import com.barbearia.Barbearia_Completo.model.UsuarioModel;
import com.barbearia.Barbearia_Completo.repository.ServicosRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ServicoService {

    private final ServicosRepository repository;

    public ServicoService(ServicosRepository repository) {
        this.repository = repository;
    }

    public ServicoResponse criar(ServicoRequest request){

        if(repository.existsByNameIgnoreCase(request.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Serviço já existente");
        }

        ServicosModel servico = new ServicosModel();
        servico.setName(request.getName());
        servico.setPrice(request.getPrice());
        servico.setDurationMinutes(request.getDurationMinutes());
        servico.setActive(true);
        ServicosModel saved = repository.save(servico);
        return new ServicoResponse(saved.getId(), saved.getName(), saved.getPrice(), saved.getDurationMinutes(), saved.getActive());
    }
    public List<ServicoResponse>listar(){
        return repository.findByActiveTrue().stream().map(servico -> new ServicoResponse(servico.getId(), servico.getName(), servico.getPrice(), servico.getDurationMinutes(),servico.getActive())).toList();
    }
  public void deletar(Long id){
        ServicosModel servico = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado"));

       servico.setActive(false);
       repository.save(servico);
  }

  public ServicoResponse atualizar(Long id, ServicoRequest request){
        ServicosModel servico = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado"));

        if(!servico.getName().equalsIgnoreCase(request.getName()) && repository.existsByNameIgnoreCase(request.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Serviço ja existente");
        }

        servico.setName(request.getName());
        servico.setPrice(request.getPrice());
        servico.setDurationMinutes(request.getDurationMinutes());
        ServicosModel saved = repository.save(servico);
        return new ServicoResponse(saved.getId(),saved.getName(),saved.getPrice(), saved.getDurationMinutes(), saved.getActive());
  }

}
