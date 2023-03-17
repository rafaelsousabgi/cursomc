package com.example.cursomc2.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cursomc2.domain.Cliente;
import com.example.cursomc2.repositories.ClienteRepository;
import com.example.cursomc2.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		/**implementando a exceção caso id não exista**/
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id:"+id+ ", Tipo: "
				+ Cliente.class.getName()));
		
	}
}
