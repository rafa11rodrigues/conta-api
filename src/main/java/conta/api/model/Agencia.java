package conta.api.model;

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

import lombok.Data;

@Entity
@Table(name = "agencia",
		uniqueConstraints = @UniqueConstraint(columnNames = {"numero", "cidade_id"}))
@Data
public class Agencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "numero", nullable = false)
	private String numero;
	
	@ManyToOne
	@JoinColumn(name = "cidade_id", referencedColumnName = "id", nullable = false)
	private Cidade cidade;

	
	public Agencia() {}
	
	public Agencia(String numero, Cidade cidade) {
		Assert.hasText(numero, "número é obrigatório");
		Assert.notNull(cidade, "a agência precisa de uma cidade");
		
		this.numero = numero;
		this.cidade = cidade;
	}
}
