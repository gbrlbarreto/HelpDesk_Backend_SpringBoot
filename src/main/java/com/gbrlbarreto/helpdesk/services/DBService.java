package com.gbrlbarreto.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbrlbarreto.helpdesk.domain.Chamado;
import com.gbrlbarreto.helpdesk.domain.Cliente;
import com.gbrlbarreto.helpdesk.domain.Tecnico;
import com.gbrlbarreto.helpdesk.domain.enums.Perfil;
import com.gbrlbarreto.helpdesk.domain.enums.Prioridade;
import com.gbrlbarreto.helpdesk.domain.enums.Status;
import com.gbrlbarreto.helpdesk.repositories.ChamadoRepository;
import com.gbrlbarreto.helpdesk.repositories.ClienteRepository;
import com.gbrlbarreto.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {
    
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

    public void instanciaDB(){
        Tecnico tec1 = new Tecnico(null, "Gabriel Barreto", "01234567890", "tecnico1@mail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Cliente 01", "98765432101", "cliente1@mail.com", "123");

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);

		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
    }
}
