package conta.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.util.Assert;

import lombok.Data;

@Entity
@Table(name = "endereco")
@Data
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
	@JoinColumn(name = "cidade_id", referencedColumnName = "id")
	private Cidade cidade;
	
	
	public Endereco() {}

	public Endereco(String logradouro, String bairro, String cep, Cidade cidade) {
		Assert.hasText(logradouro, "logradouro é obrigatório");
		Assert.hasText(bairro, "bairro é obrigatório");
		Assert.hasText(cep, "cep é obrigatório");
		Assert.isTrue(cep.matches("[0-9]{5}-[0-9]{3}"), "cep deve ter o formato: _____-___");
		Assert.notNull(cidade, "cidade é obrigatória");
		
		this.logradouro = logradouro;
		this.numero = "S/N";
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
	}
	
	public Endereco(String logradouro, String numero, String bairro, String cep, Cidade cidade) {
		this(logradouro, bairro, cep, cidade);
		
		Assert.notNull(numero, "número não pode ser nulo");
		this.numero = numero;
	}
	
	public Endereco(String logradouro, String numero, String complemento, String bairro, String cep, Cidade cidade) {
		this(logradouro, numero, bairro, cep, cidade);
		this.complemento = complemento;
	}
}
