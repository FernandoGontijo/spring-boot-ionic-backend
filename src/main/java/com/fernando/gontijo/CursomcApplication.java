package com.fernando.gontijo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fernando.gontijo.domain.Categoria;
import com.fernando.gontijo.domain.Cidade;
import com.fernando.gontijo.domain.Estado;
import com.fernando.gontijo.domain.Produto;
import com.fernando.gontijo.repositories.CategoriaRepository;
import com.fernando.gontijo.repositories.CidadeRepository;
import com.fernando.gontijo.repositories.EstadoRepository;
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
	
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));		// Associando as categorias aos produtos 
		
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		
		p1.getCategorias().addAll(Arrays.asList(cat1));				// Associando os produtos as categorias
		
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		est1.getCidades().addAll(Arrays.asList(c1));
		
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3)); 
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		
	}

	
	
	
	
	
}
