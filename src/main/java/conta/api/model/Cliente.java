package conta.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.util.Assert;

import lombok.Getter;

@Entity
@Table(name = "cliente")
@Getter
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", length = 60, nullable = false)
	private String nome;
	
	@Column(name = "cpf", length = 14, nullable = false, unique = true)
	private String cpf;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cliente_id")
	private List<Endereco> enderecos;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Conta> contas;
	
	
	public Cliente() {
		this.enderecos = new ArrayList<>();
		this.contas = new ArrayList<>();
	}

	public Cliente(String nome, String cpf) {
		this();
		
		Assert.hasText(nome, "nome não pode ser nulo nem vazio");
		Assert.hasText(cpf, "cpf não pode ser nulo nem vazio");
		Assert.isTrue(cpf.matches("[0-9]{3}[.][0-9]{3}[.][0-9]{3}-[0-9]{2}"), "cpf deve ter o formato XXX.XXX.XXX-XX");
		
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public void addEnderecos(Endereco... enderecos) {
		Assert.notNull(enderecos, "o array de endereços não pode ser null");
		Assert.isTrue(enderecos.length > 0, "deve ser passado ao menos 1 endereço");
		
		for (Endereco e: enderecos)
			this.enderecos.add(e);
	}
}
