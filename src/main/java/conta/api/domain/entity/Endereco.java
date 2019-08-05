package conta.api.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.util.Assert;

import lombok.Getter;

@Entity
@Table(name = "endereco")
@Getter
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "logradouro", nullable = false)
	private String logradouro;
	
	@Column(name = "numero", columnDefinition = "VARCHAR(30) NOT NULL DEFAULT 'S/N'")
	private String numero;
	
	@Column(name = "complemento")
	private String complemento;
	
	@Column(name = "bairro", nullable = false)
	private String bairro;
	
	@Column(name = "cep", length = 9, nullable = false)
	private String cep;
	
	@ManyToOne
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", length = 20)
	private EnderecoTipo tipo;
	
	
	public Endereco() {}

	public Endereco(String logradouro, String bairro, String cep, Cidade cidade, EnderecoTipo tipo) {
		Assert.hasText(logradouro, "logradouro não pode ser nulo nem vazio");
		Assert.hasText(bairro, "bairro não pode ser nulo nem vazio");
		Assert.hasText(cep, "cep não pode ser nulo nem vazio");
		Assert.isTrue(cep.matches("[0-9]{5}-[0-9]{3}"), "cep deve ter o formato: XXXXX-XXX");
		Assert.notNull(cidade, "cidade não pode ser nulo");
		Assert.notNull(tipo, "tipo não pode ser nulo");
		
		this.logradouro = logradouro;
		this.numero = "S/N";
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.tipo = tipo;
	}
	
	public Endereco(String logradouro, String numero, String bairro, String cep, Cidade cidade, EnderecoTipo tipo) {
		this(logradouro, bairro, cep, cidade, tipo);
		Assert.hasText(numero, "número não pode ser nulo nem vazio");
		
		this.numero = numero;
	}
	
	public Endereco(String logradouro, String numero, String complemento, String bairro, String cep, Cidade cidade, EnderecoTipo tipo) {
		this(logradouro, numero, bairro, cep, cidade, tipo);
		this.complemento = complemento;
	}
}
