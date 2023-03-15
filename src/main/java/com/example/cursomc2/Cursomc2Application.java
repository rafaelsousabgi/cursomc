package com.example.cursomc2;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.cursomc2.domain.Categoria;
import com.example.cursomc2.domain.Cidade;
import com.example.cursomc2.domain.Estado;
import com.example.cursomc2.domain.Produto;
import com.example.cursomc2.repositories.CategoriaRepository;
import com.example.cursomc2.repositories.CidadeRepository;
import com.example.cursomc2.repositories.EstadoRepository;
import com.example.cursomc2.repositories.ProdutoRepository;

@SpringBootApplication
public class Cursomc2Application implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;

	public static void main(String[] args) {
		SpringApplication.run(Cursomc2Application.class, args);
	}

	/** metodo para salvar uma categoria**/
	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 =  new Produto(null, "Computador",2000.00);
		Produto p2 =  new Produto(null," Impressora",800.00);
		Produto p3 =  new Produto(null, "Mouse",80.00);
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1,cat2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
	    produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
	    
	    Estado est1 = new Estado(null,"Minas Gerais");
	    Estado est2 = new Estado(null,"São Paulo");
	    
	    Cidade c1 = new Cidade(null,"Uberlândia",est1);
	    Cidade c2 = new Cidade(null,"São Paulo",est2);
	    Cidade c3 = new Cidade(null,"Campinas",est2);
	    
	    est1.getCidades().addAll(Arrays.asList(c1));
	    est2.getCidades().addAll(Arrays.asList(c2,c3));
	    
	    /**salvando a entidade forte, ou seja a primeira que 
	     * deve existir para a outra existir, exemplo uma cidade 
	     * exite em um estado, um estado não existe numa cidade
	     */
	    estadoRepository.saveAll(Arrays.asList(est1,est2));
	    cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
	}
	

}
