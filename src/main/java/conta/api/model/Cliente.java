package conta.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.util.Assert;

import lombok.Data;

@Entity
@Table(name = "cliente")
@Data
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", length = 60, nullable = false)
	private String nome;
	
	@Column(name = "cpf", length = 14, nullable = false, unique = true)
	private String cpf;
	
	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(name = "cliente_endereco",
				joinColumns = {@JoinColumn(name = "cliente_id", referencedColumnName = "id")},
				inverseJoinColumns = {@JoinColumn(name = "endereco_id", referencedColumnName = "id")})
	List<Endereco> enderecos;
	
	
	public Cliente() {
		this.enderecos = new ArrayList<>();
	}

	public Cliente(String nome, String cpf) {
		this();
		
		Assert.hasText(nome, "nome é obrigatório");
		Assert.hasText(cpf, "cpf é obrigatório");
		Assert.isTrue(cpf.matches("[0-9]{3}[.][0-9]{3}[.][0-9]{3}-[0-9]{2}"), "cpf deve ter formato ___.___.___-__");
		
		this.nome = nome;
		this.cpf = cpf;
	}

	public Cliente(String nome, String cpf, List<Endereco> enderecos) {
		this(nome, cpf);
		
		Assert.notNull(enderecos, "lista de endereços não pode ser nula");
		
		this.enderecos = enderecos;
	}
}
