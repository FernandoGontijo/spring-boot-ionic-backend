package com.fernando.gontijo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity                        									// Mapear a classe como tabela no BD
public class Produto implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@Id															// Maoear o atributo como ID da tabela 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double preco;
	
	@JsonBackReference													// Nao deixa buscar a lista de categoria dos produtos
	@ManyToMany															// Anotações para definir o relacionamento das tabelas
	@JoinTable(name = "PRODUTO_CATEGORIA",								// Nome da tebela que vai ligar "Categoria"e "Produto	
				joinColumns = @JoinColumn(name = "produto_id"),			// Chave Estrangeira de Produto	
				inverseJoinColumns = @JoinColumn(name = "categoria_id")	// Chave Estrangeira de Categoria 
	)
	private List<Categoria> categorias = new ArrayList<>();   // Associação do produto com a categoria
	
	@OneToMany(mappedBy="id.produto")
	private Set<ItemPedido> itens = new HashSet<>();	
	
	
	public Produto() { // Construtor vazio
		
	}

	public Produto(Integer id, String nome, Double preco) { // Construtor com Parâmetros
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	
	public List<Pedido> pedidos(){
		List<Pedido> lista = new ArrayList();
		for (ItemPedido x : itens) {
			lista.add(x.getPedido());
		}
		return lista;
	}
	
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
	
	
	

}
