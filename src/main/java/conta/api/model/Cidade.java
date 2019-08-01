package conta.api.model;

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

import lombok.Data;

@Entity
@Table(name = "cidade",
		uniqueConstraints = @UniqueConstraint(columnNames = {"nome", "estado"}))
@Data
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", length = 60, nullable = false)
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "estado", length = 2, nullable = false)
	private Estado estado;
	
	public Cidade() {}
	
	public Cidade(String nome, Estado estado) {
		Assert.hasText(nome, "O nome é obrigatório");
		Assert.notNull(estado, "O estado é obrigatório");
		
		this.nome = nome;
		this.estado = estado;
	}
}
