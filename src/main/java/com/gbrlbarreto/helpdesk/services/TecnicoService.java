package com.gbrlbarreto.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbrlbarreto.helpdesk.domain.Pessoa;
import com.gbrlbarreto.helpdesk.domain.Tecnico;
import com.gbrlbarreto.helpdesk.domain.dtos.TecnicoDTO;
import com.gbrlbarreto.helpdesk.repositories.PessoaRepository;
import com.gbrlbarreto.helpdesk.repositories.TecnicoRepository;
import com.gbrlbarreto.helpdesk.services.exceptions.DataIntegrityViolationExcepetion;
import com.gbrlbarreto.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
    
    @Autowired
    private TecnicoRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null);
        validaPorCpfEEmail(objDTO);
        Tecnico newObj = new Tecnico(objDTO);
        return repository.save(newObj);
    }

    private void validaPorCpfEEmail(TecnicoDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationExcepetion("CPF já cadastrado no sistema!");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationExcepetion("E-mail já cadastrado no sistema!");
        }
    }
}
