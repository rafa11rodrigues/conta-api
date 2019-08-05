package conta.api.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.util.Assert;

import lombok.Getter;

@Entity
@Table(name = "cidade",
		uniqueConstraints = @UniqueConstraint(columnNames = {"nome", "uf"}))
@Getter
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", length = 60, nullable = false)
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "uf", length = 2, nullable = false)
	private Estado uf;
	
	public Cidade() {}
	
	public Cidade(String nome, Estado uf) {
		Assert.hasText(nome, "nome não pode ser nulo nem vazio");
		Assert.notNull(uf, "uf não pode ser nulo");
		
		this.nome = nome;
		this.uf = uf;
	}
	
	public void alterar(String nome, Estado uf) {
		this.nome = nome;
		this.uf = uf;
	}
}
