package com.example.cursomc2.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cursomc2.domain.Pedido;
import com.example.cursomc2.repositories.PedidoRepository;
import com.example.cursomc2.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		/**implementando a exceção caso id não exista**/
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id:"+id+ ", Tipo: "
				+ Pedido.class.getName()));
		
	}
}
