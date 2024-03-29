package com.example.cursomc2;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.cursomc2.domain.Categoria;
import com.example.cursomc2.domain.Cidade;
import com.example.cursomc2.domain.Cliente;
import com.example.cursomc2.domain.Endereco;
import com.example.cursomc2.domain.Estado;
import com.example.cursomc2.domain.ItemPedido;
import com.example.cursomc2.domain.Pagamento;
import com.example.cursomc2.domain.PagamentoComBoleto;
import com.example.cursomc2.domain.PagamentoComCartao;
import com.example.cursomc2.domain.Pedido;
import com.example.cursomc2.domain.Produto;
import com.example.cursomc2.domain.enums.EstadoPagamento;
import com.example.cursomc2.domain.enums.TipoCliente;
import com.example.cursomc2.repositories.CategoriaRepository;
import com.example.cursomc2.repositories.CidadeRepository;
import com.example.cursomc2.repositories.ClienteRepository;
import com.example.cursomc2.repositories.EnderecoRepository;
import com.example.cursomc2.repositories.EstadoRepository;
import com.example.cursomc2.repositories.ItemPedidoRepository;
import com.example.cursomc2.repositories.PagamentoRepository;
import com.example.cursomc2.repositories.PedidoRepository;
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
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

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
	    
	    Cliente cli1 = new Cliente(null,"Maria Silva","maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
	    		
	    cli1.getTelefone().addAll(Arrays.asList("24459895","234432432"));
	    
	    Endereco e1= new Endereco(null,"Rua Flores","300" ,"Aptp 303","Jardim","38220834", cli1, c1);	    
	    Endereco e2= new Endereco(null,"Avenida Matos","150" ,"sala 800","Centro","38220834", cli1, c2);
	    
	    cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
	    
	    clienteRepository.saveAll(Arrays.asList(cli1));
	    enderecoRepository.saveAll(Arrays.asList(e1,e2));
	    
	    /**formatando um formato de data**/
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	    
	    Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32" ) ,cli1, e2);
	    Pedido ped2 = new Pedido(null,sdf.parse("10/10/2017 00:00"), cli1, e1);
	    
	    Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,ped1,6);
	    ped1.setPagamento(pagto1);
	    
	    Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE , ped2, sdf.parse("20/10/2017 04:23"), null);
	    ped2.setPagamento(pagto2);
	    
	    cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
	    
	    pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
	    pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
	    
	    ItemPedido ip1 = new ItemPedido(ped2, p1, 0.00, 1, 2000.00);
	    ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
	    ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
	    
	    ped1.getItens().addAll(Arrays.asList(ip1,ip2));
	    ped2.getItens().addAll(Arrays.asList(ip3));
	    
	    p1.getItens().addAll(Arrays.asList(ip1));
	    p2.getItens().addAll(Arrays.asList(ip3));
	    p3.getItens().addAll(Arrays.asList(ip2));
	    
	    itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	    
	}
	

}
