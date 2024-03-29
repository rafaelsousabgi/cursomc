package com.example.cursomc2.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cursomc2.domain.Categoria;
import com.example.cursomc2.repositories.CategoriaRepository;
import com.example.cursomc2.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		/**implementando a exceção caso id não exista**/
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id:"+id+ ", Tipo: "
				+ Categoria.class.getName()));
		
	}
}
