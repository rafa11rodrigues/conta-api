package conta.api.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.util.Assert;

import lombok.Getter;

@Entity
@Table(name = "agencia",
		uniqueConstraints = {@UniqueConstraint(columnNames = {"numero", "cidade_id"}),
							 @UniqueConstraint(columnNames = {"numero", "digito"})}
)
@Getter
public class Agencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "numero", nullable = false)
	private String numero;
	
	@Column(name = "digito", nullable = false)
	private String digito;
	
	@ManyToOne
	@JoinColumn(name = "cidade_id", nullable = false)
	private Cidade cidade;

	
	public Agencia() {}
	
	public Agencia(String numero, String digito, Cidade cidade) {
		Assert.hasText(numero, "número não pode ser nulo nem vazio");
		Assert.hasText(digito, "dígito não pode ser nulo nem vazio");
		Assert.notNull(cidade, "a agência precisa de uma cidade");
		
		this.numero = numero;
		this.digito = digito;
		this.cidade = cidade;
	}
}
