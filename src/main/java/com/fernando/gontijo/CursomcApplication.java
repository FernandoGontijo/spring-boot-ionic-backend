package com.fernando.gontijo;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fernando.gontijo.domain.Categoria;
import com.fernando.gontijo.domain.Cidade;
import com.fernando.gontijo.domain.Cliente;
import com.fernando.gontijo.domain.Endereco;
import com.fernando.gontijo.domain.Estado;
import com.fernando.gontijo.domain.ItemPedido;
import com.fernando.gontijo.domain.Pagamento;
import com.fernando.gontijo.domain.PagamentoComBoleto;
import com.fernando.gontijo.domain.PagamentoComCartao;
import com.fernando.gontijo.domain.Pedido;
import com.fernando.gontijo.domain.Produto;
import com.fernando.gontijo.domain.enums.EstadoPagamento;
import com.fernando.gontijo.domain.enums.TipoCliente;
import com.fernando.gontijo.repositories.CategoriaRepository;
import com.fernando.gontijo.repositories.CidadeRepository;
import com.fernando.gontijo.repositories.ClienteRepository;
import com.fernando.gontijo.repositories.EnderecoRepository;
import com.fernando.gontijo.repositories.EstadoRepository;
import com.fernando.gontijo.repositories.ItemPedidoRepository;
import com.fernando.gontijo.repositories.PagamentoRepository;
import com.fernando.gontijo.repositories.PedidoRepository;
import com.fernando.gontijo.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;  // Criar depêndencias
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired 
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama, mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393")); // ligar os telefones ao cliente
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");  // Mascara deformatacao para isntaciar uma data
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 00.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 00.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
			
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));		// Associando as categorias aos produtos 
		
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		
		p1.getCategorias().addAll(Arrays.asList(cat1));				// Associando os produtos as categorias
		
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		est1.getCidades().addAll(Arrays.asList(c1));
		
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));

		p2.getItens().addAll(Arrays.asList(ip3));

		p3.getItens().addAll(Arrays.asList(ip2));
		
		
		
		
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3)); 
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
		
		
	}

	
	
	
	
	
}
