package br.com.sobre.sobremesa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.sobre.sobremesa.model.Sobremesa;
import br.com.sobre.sobremesa.repository.SobremesaRepository;

@Service
public class SobremesaService {
    private final SobremesaRepository repository;

    public SobremesaService(SobremesaRepository repository) {
        this.repository = repository;
    }

    public Sobremesa create(Sobremesa s){
        return repository.save(s);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
    
    public Sobremesa update(Sobremesa s){
        return repository.saveAndFlush(s);
    }

    public List<Sobremesa> findByDeletedIsNull(){
        return repository.findByDeletedIsNull();
    }

    public Sobremesa findById(Long id){
        Optional<Sobremesa> s = repository.findById(id);
        return s.orElse(null);
    }
    public List<Sobremesa> findAll(){
        return repository.findAll();
    }
}
